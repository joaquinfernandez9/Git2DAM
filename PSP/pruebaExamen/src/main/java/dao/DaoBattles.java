//package dao;
//
//import config.DatabaseConnection;
//import jakarta.inject.Inject;
//import lombok.extern.log4j.Log4j2;
//import model.*;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.*;
//import java.util.Collections;
//import java.util.List;
//
//@Log4j2
//public class DaoBattles {
//    private final DatabaseConnection db;
//    private final DaoFaction daoFaction;
//    //Insert a new battle, inserting faction if it does not exist
//    private final String addBattle = "insert into battles (id, bname, faction_one, faction_two, bplace, bdate, id_spy) values (?,?,?,?,?,?,?)";
//
//    @Inject
//    public DaoBattles(DatabaseConnection db, DaoFaction daoFaction) {
//        this.daoFaction = daoFaction;
//        this.db = db;
//    }
//
//    public final  String getByName = "select * from faction where fname=?";
//    private  Faction getByName(String fname){
//        List<Faction> list;
//        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
//        list = jtm.query(query2,
//                BeanPropertyRowMapper.newInstance(Faction.class),fname);
//        return list.get(0);
//    }
//
//    public int save(Battle battle, Faction fac1, Faction fac2) {
//        int response;
//        try (Connection con = db.getConnection();
//             PreparedStatement ps = con.prepareStatement(addBattle)) {
//
//            con.setAutoCommit(false);
//
//            //comprobar faction no existente
//            //si existe se deja pasar
//            Faction f1 = getByName(battle.getFaction_one());
//            Faction f2 =  getByName(battle.getFaction_two());
//            if (f1 == null) {
//                int responseFacOne = daoFaction.save(fac1);
//                if (responseFacOne != 1) {
//                    con.rollback();
//                }
//            }
//            if (f2 == null) {
//                int responseFacOne = daoFaction.save(fac2);
//                if (responseFacOne != 1) {
//                    con.rollback();
//                }
//            }
//            ps.setInt(1, battle.getId());
//            ps.setString(2, battle.getBname());
//            ps.setString(3, battle.getFaction_one());
//            ps.setString(4, battle.getFaction_two());
//            ps.setString(5, battle.getBplace());
//            ps.setDate(6, Date.valueOf(battle.getBdate()));
//            ps.setInt(7, battle.getId_spy());
//            response = ps.executeUpdate();
//            con.commit();
//        } catch (SQLException e) {
//            response = -3;
//            log.error(e.getMessage());
//        }
//        return response;
//    }
//
//    private String query2 = "select bname, spies.sname from battles, spies where battles.id_spy = spies.id and spies.sname = ?";
//    public List<BnameSname> getBattles(Spy spy){
//        List<BnameSname> list;
//        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
//        list = jtm.query(query2,
//                BeanPropertyRowMapper.newInstance(BnameSname.class), spy.getSname());
//        return list;
//    }
//
//    private String getBattles = "select * from battles";
//    public List<Battle> getAll(){
//        List<Battle> list;
//        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
//        list = jtm.query(getBattles,
//                BeanPropertyRowMapper.newInstance(Battle.class));
//        return list;
//    }
//
//}
