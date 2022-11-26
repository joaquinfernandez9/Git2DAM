package dao;

import config.DatabaseConnection;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.QueryOne;
import model.Weapon;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class DaoWeapons {

    private final DatabaseConnection db;
    //Alice's cousin damaged his brain in a battle. To keep him busy, he is in charge of
    //recording weapons of the rebels; to avoid mistakes, the program will delete the insertion
    //immediately.
    private String addQuery = "insert into weapons (wname, wprice) values (?, ?)";
    private String deleteQuery = "delete from weapons where id=?";
    //update price of weapon
    private String update = "UPDATE weapons set wprice=? where id = ?";

    @Inject
    public DaoWeapons(DatabaseConnection db) {
        this.db = db;
    }

    public int notSave(Weapon weapon) {
        int response;
        try (Connection con = db.getConnection();
             PreparedStatement statement = con.prepareStatement(addQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            //query to add to weapons factions too, so this recieves the
            statement.setString(1, weapon.getName());
            statement.setDouble(2, weapon.getPrice());
            //no se si es un not save que añada y borre
            response = statement.executeUpdate();
            //get id generado
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                weapon.setId(rs.getInt(1));
            }
            ps.setInt(1, weapon.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            response = -3;
            log.error(e.getMessage());
        }
        return response;
    }

    public int update(Weapon weapon) {
        int repsonse;
        try (Connection con = db.getConnection();
             PreparedStatement statement = con.prepareStatement(update)) {
            statement.setDouble(1, weapon.getPrice());
            statement.setInt(2, weapon.getId());
            repsonse = statement.executeUpdate();
        } catch (SQLException e) {
            repsonse = -3;
            log.error(e.getMessage());
        }
        return repsonse;
    }

    //select the data of all weapons
    private String selectAllWeaponsQuery = "select weapons.*, faction.fname from weapons, weapons_factions, faction where faction.fname = weapons_factions.name_faction and weapons_factions.id_weapon = weapons.id and faction.date_last_purchase = ?";
    public List<QueryOne> queryOne(LocalDate date){
        List<QueryOne> list;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        list = jtm.query(selectAllWeaponsQuery,
                BeanPropertyRowMapper.newInstance(QueryOne.class), date);
        return list;

    }

}
