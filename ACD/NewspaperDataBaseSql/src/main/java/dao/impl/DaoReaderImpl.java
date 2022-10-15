package dao.impl;

import dao.DaoReader;
import domain.modelo.Reader;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoReaderImpl implements DaoReader {

    private final DaoDB db;

    @Inject
    public DaoReaderImpl(DaoDB db) {
        this.db = db;
    }

    @Override
    public Either<String, List<Reader>> getAll() {
        Either<String, List<Reader>> response = null;
        try (Connection con = db.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = statement.executeQuery("select * from reader");
            if (rs != null) {
                response = Either.right(readRS(rs));
            } else {
                response = Either.left("error");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public Either<String, Reader> get(int id) {
        Either<String, Reader> response = null;
        try (Connection con = db.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = statement.executeQuery("select * from reader where id= ?");
            if (rs != null) {
                response = Either.right(readRS(rs).get(0));
            } else {
                response = Either.left("error");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public int delete(int id) {
        int response = 0;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "delete from reader where id = ?")) {
            preparedStatement.setInt(1, id);
            // executeUpdate method for INSERT, UPDATE and DELETE
            response = preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            Logger.getLogger(DaoReaderImpl.class.getName())
                    .log(Level.SEVERE, null, sqle);
        }
        return response;
    }

    @Override
    public boolean update(int id, String name) {
        boolean response = false;
        try (Connection connection = db.getConnection()) {
            if (name != null) {
                //cambiar name
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "UPDATE reader set name_reader =? where id = ?")) {

                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, id);

                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(DaoReaderImpl.class.getName())
                            .log(Level.SEVERE, null, e);
                }
//            } else {
//                cambiar fecha
//                try (PreparedStatement preparedStatement = connection.prepareStatement(
//                        "UPDATE reader set birth_date = ? where id = ?");){
//
//                    preparedStatement.setDate(1, Date.valueOf(date));
//                    preparedStatement.setInt(2, id);
//
//                } catch (SQLException e){
//                    Logger.getLogger(DaoReaderImpl.class.getName())
//                            .log(Level.SEVERE, null, e);
//                }

            }
            response = true;
        } catch (SQLException e) {
            Logger.getLogger(DaoReaderImpl.class.getName())
                    .log(Level.SEVERE, null, e);
        }


        return response;
    }

    @Override
    public int add(int id, String name, LocalDate date) {
        int response = 0;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO readers (id, name_reader, birth_reader) VALUES (?,?,?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setDate(3, Date.valueOf(date));

            response = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage());
//            System.err.format("SQL State: %s%n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private List<Reader> readRS(ResultSet rs) {
        List<Reader> response = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name_reader");
                LocalDate date = rs.getDate("birth_reader")
                        .toLocalDate();
                Reader r = new Reader(id, name, date);
                response.add(r);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }


}
