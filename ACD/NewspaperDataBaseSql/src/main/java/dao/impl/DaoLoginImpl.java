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

    @Override public boolean login(Reader r){

    }

    private final DataBaseConnectionPool pool;

    @Inject
    public DaoLoginImpl(DataBaseConnectionPool pool) {
        this.pool = pool;
    }

//    public Either<Integer, List<Login>> getAll() {
//        Either<Integer, List<Login>> response;
//        try (Connection con =pool.getConnection();
//             PreparedStatement statement = con.prepareStatement(
//                     "select * from login"
//             )) {
//            ResultSet rs = statement.executeQuery();
//            response = Either.right(readRS(rs));
//
//        } catch (SQLException ex){
//            log.error(ex.getMessage());
//            response = Either.left(-3);
//        }
//        return response;
//    }

    public Either<Integer, Login> get(int idReader) {
        Either<Integer, Login> response;
        try (Connection con =pool.getConnection();
             PreparedStatement statement = con.prepareStatement(
                     "select * from login where id_reader = ?"
             )) {
            statement.setInt(1, idReader);
            ResultSet rs = statement.executeQuery();
            if (rs!=null){
                response = Either.right(readRS(rs).get(0));
            } else {
                response = Either.left(-5);
            }

        } catch (SQLException ex){
            log.error(ex.getMessage());
            response = Either.left(-3);
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
