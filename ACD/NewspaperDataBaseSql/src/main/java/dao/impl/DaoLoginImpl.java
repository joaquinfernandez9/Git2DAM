package dao.impl;

import config.ConfigYaml;
import dao.DaoLogin;
import dao.dataBase.DaoDB;
import dao.dataBase.DataBaseConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Login;
import model.Reader;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        try (Connection con = pool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "Select id_reader from login where user=? and password =?"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                response = rs.getInt("id_reader");
            } else {
                response = 0;
            }


        } catch (SQLException sql) {
            response = -3;
        }
        return response;

    }


    @Override
    public int get(String username, String password) {
        int response;
        try (Connection con =pool.getConnection();
             PreparedStatement statement = con.prepareStatement(
                     "select id_reader from login where user=? and password =?"
             )) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                response = rs.getInt("id_reader");
            } else {
                response = -5;
            }

        } catch (SQLException ex){
            log.error(ex.getMessage());
            response = -3;
        }
        return response;
    }

    private List<Login> readRS(ResultSet rs) {
        List<Login> response = new ArrayList<>();
        try {
            while (rs.next()) {
                String username = rs.getString("user");
                String password = rs.getString("password");
                Login log = new Login(username, password);
                response.add(log);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }


}
