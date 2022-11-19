package dao.impl;

import domain.modelo.DatabaseException;
import domain.modelo.DatabaseIntegrityViolation;
import domain.modelo.DatabaseRollbackException;
import domain.modelo.NotFoundException;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import dao.Const;
import dao.DaoReader;
import dao.dataBase.DataBaseConnectionPool;
import model.Login;
import model.Reader;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoReaderImpl implements DaoReader {


    private final DataBaseConnectionPool pool;

    @Inject
    public DaoReaderImpl(DataBaseConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public List<Reader> getAll(int idNews, String description) {
        List<Reader> response;
        try (Connection con = pool.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             PreparedStatement getAllSubToNewspaper = con.prepareStatement(Const.getAllReadersSubToNP);
             PreparedStatement getAllOfType = con.prepareStatement(Const.getAllReadersOfAType)
        ) {
            if (idNews == -1) {
                //get all
                ResultSet rs = statement.executeQuery(Const.getAllReaders);
                response = readRS(rs);
            } else if (description == null) {
                //1a2 Get information about the readers subscribed to a specific newspaper
                getAllSubToNewspaper.setInt(1, idNews);
                ResultSet rs = getAllSubToNewspaper.executeQuery();
                response = readRS(rs);
            } else {
                //1a3 Get the readers of articles of a specific type
                getAllOfType.setString(1, description);
                ResultSet rs = getAllOfType.executeQuery();
                response = readRS(rs);
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
        return response;
    }

    @Override
    public Reader get(int id) {
        Reader response;
        try (Connection con = pool.getConnection();
             PreparedStatement statement = con.prepareStatement(
                     Const.getReader)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs != null) {
                response = readRS(rs).get(0);
            } else {
                throw new NotFoundException("No reader with id " + id);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
            throw new DatabaseException(ex.getMessage());
        }
        return response;
    }

    @Override
    public void delete(int id) {
        try (Connection con = pool.getConnection()) {
            deleteQuery(con, id);
        } catch (SQLException sqle) {
            Logger.getLogger(DaoReaderImpl.class.getName())
                    .log(Level.SEVERE, null, sqle);
        }

    }

    private void deleteQuery(Connection con, int id) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(Const.DELETE_FROM_READER_WHERE_ID);
             PreparedStatement preparedStatement1 = con.prepareStatement(Const.DELETE_FROM_READARTICLE_WHERE_ID_READER);
             PreparedStatement preparedStatement2 = con.prepareStatement(Const.DELETE_FROM_SUBSCRIBE_WHERE_ID_READER);
             PreparedStatement preparedStatement3 = con.prepareStatement(Const.DELETE_FROM_LOGIN_WHERE_ID_READER)) {
            con.setAutoCommit(false);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
            preparedStatement2.setInt(1, id);
            preparedStatement2.executeUpdate();
            preparedStatement3.setInt(1, id);
            preparedStatement3.executeUpdate();
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException exec) {
            con.rollback();
            throw new DatabaseException("No reader could be deleted");
        }
    }

    @Override
    public Reader update(Reader r) {
        Reader response;
        Reader real = get(r.getId());
        try (Connection connection = pool.getConnection()) {
            response = updateQuery(connection, real, r);
        } catch (SQLException e) {
            throw new DatabaseException("Nothing could be done");
        }
        return response;
    }

    private Reader updateQuery(Connection connection, Reader real, Reader r) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Const.updateReader);
             PreparedStatement pS = connection.prepareStatement(Const.updateLogin
             )) {
            //reader
            if (r.getBirth_reader() == null) {
                preparedStatement.setDate(2, Date.valueOf(real.getBirth_reader()));
            } else {
                preparedStatement.setDate(2, Date.valueOf(r.getBirth_reader()));
            }
            if (r.getName_reader() == null) {
                preparedStatement.setString(1, real.getName_reader());
            } else {
                preparedStatement.setString(1, r.getName_reader());
            }
            preparedStatement.setInt(3, r.getId());

            //login
            if (r.getLogin().getUser() == null) {
                pS.setString(1, real.getLogin().getUser());
            } else {
                pS.setString(1, r.getLogin().getPassword());
            }
            if (r.getLogin().getPassword() == null) {
                pS.setString(2, real.getLogin().getPassword());
            } else {
                pS.setString(2, r.getLogin().getPassword());
            }
            pS.setInt(3, r.getId());
            preparedStatement.executeUpdate();
            return get(r.getId());
        } catch (SQLException e) {
            throw new DatabaseIntegrityViolation("No reader could be updated");
        }
    }

    @Override
    public Reader add(Reader r) {
        Reader response;
        try (Connection con = pool.getConnection()) {
            response = addQuery(con, r);
        } catch (SQLException e) {
            throw new DatabaseIntegrityViolation("No reader could be added");
        }
        return response;
    }

    private Reader addQuery(Connection con, Reader r) {
        Reader response;
        try (PreparedStatement preparedStatement = con.prepareStatement(Const.addReader, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pS = con.prepareStatement(Const.addLogin, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            preparedStatement.setString(1, r.getName_reader());
            preparedStatement.setDate(2, Date.valueOf(r.getBirth_reader()));
            pS.setString(1, r.getLogin().getUser());
            pS.setString(2, r.getLogin().getPassword());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                r.setId(rs.getInt(1));
            }
            pS.setInt(3, r.getId());
            response = addCommit(pS, con, r);
        } catch (SQLException e) {
            try {
                con.rollback();
                throw new DatabaseException(e.getMessage());
            } catch (SQLException exception) {
                log.error(exception.getMessage());
                throw new DatabaseRollbackException(exception.getMessage());
            }
        }
        return response;
    }

    private Reader addCommit(PreparedStatement pS, Connection con, Reader r) throws SQLException {
        Reader response;
        try {
            pS.executeUpdate();
            response = r;
            con.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            con.rollback();
            log.error(e.getMessage());
            throw new DatabaseIntegrityViolation("User already exists");
        }
        return response;
    }

    private List<Reader> readRS(ResultSet rs) {
        List<Reader> response = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt(Const.ID);
                String name = rs.getString(Const.NAME_READER);
                LocalDate date = rs.getDate(Const.BIRTH_READER)
                        .toLocalDate();
                String username = rs.getString(Const.USER);
                String password = rs.getString(Const.PASSWORD);
                Login login = new Login(username, password);
                Reader r = new Reader(id, name, date, login);
                response.add(r);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }


}
