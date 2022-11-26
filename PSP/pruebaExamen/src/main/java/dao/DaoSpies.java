package dao;

import config.DatabaseConnection;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class DaoSpies {
    private final DatabaseConnection db;

    @Inject
    public DaoSpies(DatabaseConnection connectionPool) {
        this.db = connectionPool;
    }

    //delete spy
    private String deleteSpyQuery = "DELETE FROM spies WHERE id = ?";
    private String deleteBattleQuery = "DELETE FROM battles WHERE id_spy = ?";
    public int deleteSpy(int idSpy) {
        int response;
        try (Connection con = db.getConnection();
             PreparedStatement deleteSpy = con.prepareStatement(deleteSpyQuery);
             PreparedStatement deleteBattle = con.prepareStatement(deleteBattleQuery)) {
            con.setAutoCommit(false);
            deleteSpy.setInt(1, idSpy);
            deleteBattle.setInt(1, idSpy);
            deleteBattle.executeUpdate();
            response = deleteSpy.executeUpdate();
            con.commit();

        } catch (SQLException e){
            response = -3;
            log.error(e.getMessage());
        }
        return response;
    }



}
