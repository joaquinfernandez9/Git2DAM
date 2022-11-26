package dao;

import config.Config;
import config.DatabaseConnection;
import jakarta.inject.Inject;
import jakarta.transaction.Transaction;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.log4j.Log4j2;
import model.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public class DaoFaction {

    public final String getAllQuery = "select * from faction";
    public final String getByName = "select * from faction where fname=?";
    private final DatabaseConnection db;
    //load to database
    private final String saveList = "insert into faction (fname, contact, planet, number_controlled_systems, date_last_purchase) values (?, ? ,? ,? ,?)";
    private final String addBattle = "insert into battles (id, bname, faction_one, faction_two, bplace, bdate, id_spy) values (?,?,?,?,?,?,?)";
    private final String updateFaction = "update faction set fname=? where fname=?";
    private final String updateBattlesF1 = "update battles set faction_one=? where id=?";
    private final String updateBattlesF2 = "update battles set faction_two=? where id=?";
    private final String getWeaponsFactions = "update weapons_factions set name_faction=? where id=?";
    //delete all weapons that are not sold
    public String deleteWeapons = "delete weapons, wf from weapons inner join weapons_factions wf on weapons.id = wf.id_weapon inner join sales s on wf.id = s.id_weapons_faction where s.units = 0";
    private String query2 = "select bname, spies.sname from battles, spies where battles.id_spy = spies.id and spies.sname = ?";
    private String getBattles = "select * from battles";
    private String selectWeaponsFactionsByName = "select * from weapons_factions where name_faction = ?";

    @Inject
    public DaoFaction(DatabaseConnection con) {
        this.db = con;
    }

    public Factions getAllFactionsXML() {
        Factions factionList;
        try {
            Path xmlFile = Paths.get(Config.getInstance().getProperty("xmlFactionsPath"));
            JAXBContext jaxbContext = JAXBContext.newInstance(Factions.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            factionList = (Factions) unmarshaller
                    .unmarshal(Files.newInputStream(xmlFile));
        } catch (Exception e) {
            e.printStackTrace();
            factionList = null;
        }
        return factionList;
    }

    public int saveFactionsToDatabase() {
        int response;
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement(saveList);
            //autocommit false para que si falla al meter algun elemento que se pudra?

            //leer lista y por cada faction ejecutar la query
            List<Faction> list = getAllFactionsXML().getFaction();
            list.forEach(faction -> {
                try {
                    ps.setString(1, faction.getName());
                    ps.setString(2, faction.getContact());
                    ps.setString(3, faction.getPlanet());
                    ps.setInt(4, faction.getNumberCS());
                    ps.setDate(5, Date.valueOf(faction.getDateLastPurchase()));
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            response = 1;
        } catch (SQLException e) {
            response = -3;
            log.error(e.getMessage());
        }
        return response;
    }

    public int saveFaction(Faction faction) {
        int response;
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(saveList)) {

            ps.setString(1, faction.getName());
            ps.setString(2, faction.getContact());
            ps.setString(3, faction.getPlanet());
            ps.setInt(4, faction.getNumberCS());
            ps.setDate(5, Date.valueOf(faction.getDateLastPurchase()));
            response = ps.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage());
            response = -3;
        }
        return response;
    }

    public List<Faction> getAllFactions() {
        List<Faction> factions;
        try (Connection con = db.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = statement.executeQuery(getAllQuery);
            if (rs != null) {
                factions = readRS(rs);
            } else {
                factions = Collections.emptyList();
            }
        } catch (SQLException exception) {
            factions = Collections.emptyList();
            log.error(exception.getMessage());
        }
        return factions;
    }

    private List<Faction> readRS(ResultSet rs) {
        List<Faction> result = new ArrayList<>();
        try {
            while (rs.next()) {
                String nombre = rs.getString("fname");
                String contact = rs.getString("contact");
                String planet = rs.getString("planet");
                int number_controlled_systems = rs.getInt("number_controlled_systems");
                Date date_last_purchase = rs.getDate("date_last_purchase");
                Faction faction = new Faction(nombre, contact, planet, number_controlled_systems, date_last_purchase.toLocalDate());
                result.add(faction);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public int update(String oldName, String newName) {
        int response;
        //spring transaction false
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(db.getDataSource());
        org.springframework.transaction.TransactionStatus txStatus = txManager.getTransaction(txDef);
        try {
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
            System.out.println(1);
            //get weapons_faction by id
            List<WeaponsFactions> weaponsFactions = jtm.query(selectWeaponsFactionsByName,
                    BeanPropertyRowMapper.newInstance(WeaponsFactions.class), oldName);
            weaponsFactions.forEach(weaponsFaction -> jtm.update(getWeaponsFactions, newName, weaponsFaction.getId()));

            List<Battle> battles = getAllBattles();
            battles.forEach(battle -> {
                if (battle.getFaction_one().equalsIgnoreCase(oldName)) {
                    System.out.println(2);
                    jtm.update(updateBattlesF1, newName, battle.getId());
                }
                if (battle.getFaction_two().equalsIgnoreCase(oldName)) {
                    System.out.println(3);
                    jtm.update(updateBattlesF2, newName, battle.getId());
                }
            });
            response = jtm.update(updateFaction, newName, oldName);
            txManager.commit(txStatus);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = -3;
            txManager.rollback(txStatus);
        }

        return response;
    }

    private Faction getByName(String fname) {
        List<Faction> list;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        list = jtm.query(query2,
                BeanPropertyRowMapper.newInstance(Faction.class), fname);
        return list.get(0);
    }

    public int saveBattle(Battle battle, Faction fac1, Faction fac2) {
        int response;
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(addBattle)) {

            con.setAutoCommit(false);

            //comprobar faction no existente
            //si existe se deja pasar
            Faction f1 = getByName(battle.getFaction_one());
            Faction f2 = getByName(battle.getFaction_two());
            if (f1 == null) {
                int responseFacOne = saveFaction(fac1);
                if (responseFacOne != 1) {
                    con.rollback();
                }
            }
            if (f2 == null) {
                int responseFacOne = saveFaction(fac2);
                if (responseFacOne != 1) {
                    con.rollback();
                }
            }
            ps.setInt(1, battle.getId());
            ps.setString(2, battle.getBname());
            ps.setString(3, battle.getFaction_one());
            ps.setString(4, battle.getFaction_two());
            ps.setString(5, battle.getBplace());
            ps.setDate(6, Date.valueOf(battle.getBdate()));
            ps.setInt(7, battle.getId_spy());
            response = ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            response = -3;
            log.error(e.getMessage());
        }
        return response;
    }

    public List<BnameSname> getBattles(Spy spy) {
        List<BnameSname> list;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        list = jtm.query(query2,
                BeanPropertyRowMapper.newInstance(BnameSname.class), spy.getSname());
        return list;
    }

    public List<Battle> getAllBattles() {
        List<Battle> list;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        list = jtm.query(getBattles,
                BeanPropertyRowMapper.newInstance(Battle.class));
        return list;
    }

    public int deleteWeaponsNotSold() {
        int response;
        JdbcTemplate template = new JdbcTemplate(db.getDataSource());
        response = template.update(deleteWeapons);
        return response;
    }

    //delete all data of a faction
    private String deleteFaction = "DELETE FROM factions WHERE fname = ?";
    private String deleteWeaponsFactions = "DELETE FROM weapons_factions WHERE fname = ?";
    private String deleteBattles = "DELETE FROM battles WHERE faction_one = ? OR faction_two = ?";
    public int deleteFaction(String factionName){
        int response;
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(db.getDataSource());
        org.springframework.transaction.TransactionStatus txStatus = txManager.getTransaction(txDef);
        try {
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
            jtm.update(deleteBattles, factionName, factionName);
            jtm.update(deleteWeaponsFactions, factionName);
            response = jtm.update(deleteFaction, factionName);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = -3;
            txManager.rollback(txStatus);
        }
        return response;
    }

    //select the name and the price of all the weapons of a faction


}
