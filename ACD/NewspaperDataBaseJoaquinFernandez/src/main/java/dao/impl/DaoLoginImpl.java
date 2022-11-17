package dao.impl;

import dao.Const;
import dao.DaoLogin;
import dao.dataBase.DataBaseConnectionPool;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoLoginImpl implements DaoLogin {

    private final DataBaseConnectionPool pool;

    @Inject
    public DaoLoginImpl(DataBaseConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public int login(String username, String password) {
        int response;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(Const.logQuery)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                response = rs.getInt(Const.ID_READER);
            } else {
                response = -5;
            }
        } catch (SQLException sql) {
            log.error(sql.getMessage());
            response = -3;
        }
        return response;

    }


    @Override
    public int get(String username, String password) {
        int response;
        try (Connection con = pool.getConnection();
             PreparedStatement statement = con.prepareStatement(Const.getLoginID)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                response = rs.getInt(Const.ID_READER);
            } else {
                response = -5;
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage());
            response = -3;
        }
        return response;
    }


}
