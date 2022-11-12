package dao.impl;

import dao.DaoReader;
import dao.dataBase.DataBaseConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
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
    public Either<Integer, List<Reader>> getAll(int idNews, String description) {
        Either<Integer, List<Reader>> response;
        try (Connection con = pool.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            if (idNews == -1) {
                //get all
                ResultSet rs = statement.executeQuery("select * from reader inner join login l on reader.id = l.id_reader where id> 0");
                response = Either.right(readRS(rs));
            }  else if (description ==null) {
                //1a2 Get information about the readers subscribed to a specific newspaper
                PreparedStatement pS = con.prepareStatement(
                        "select * from reader inner join login on reader.id = login.id_reader inner join subscribe on reader.id = subscribe.id_reader inner join newspaper on newspaper.id = subscribe.id_newspaper where reader.id = subscribe.id_reader and newspaper.id = subscribe.id_newspaper and newspaper.id = ?");
                pS.setInt(1, idNews);
                ResultSet rs = pS.executeQuery();
                response = Either.right(readRS(rs));
            } else {
                //1a3 Get the readers of articles of a specific type
                PreparedStatement ps = con.prepareStatement("select * from reader inner join login on reader.id = login.id_reader inner join subscribe on reader.id = subscribe.id_reader inner join newspaper on newspaper.id = subscribe.id_newspaper inner join article on newspaper.id = article.id_newspaper inner join type on article.id_type = type.id where subscribe.cancellation_date IS NULL and description = ?");
                ps.setString(1, description);
                ResultSet rs = ps.executeQuery();
                response = Either.right(readRS(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
            response = Either.left(-3);
        }
        return response;
    }

    @Override
    public Either<Integer, Reader> get(int id) {
        Either<Integer, Reader> response;
        try (Connection con = pool.getConnection();
             PreparedStatement statement = con.prepareStatement(
                     "select * from reader inner join login on reader.id = login.id_reader where reader.id = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs != null) {
                response = Either.right(readRS(rs).get(0));
            } else {
                response = Either.left(-5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
            response = Either.left(-3);
        }
        return response;
    }

    @Override
    public int delete(int id) {
        int response;
        try (Connection con = pool.getConnection()) {
            response = deleteQuery(con, id);
        } catch (SQLException sqle) {
            Logger.getLogger(DaoReaderImpl.class.getName())
                    .log(Level.SEVERE, null, sqle);
            response = -3;
        }

        return response;
    }

    private int deleteQuery(Connection con, int id) throws SQLException {
        int response;
        try (PreparedStatement preparedStatement = con.prepareStatement("delete from reader where id=?");
             PreparedStatement preparedStatement1 = con.prepareStatement("delete from readarticle where id_reader =?");
             PreparedStatement preparedStatement2 = con.prepareStatement("delete from subscribe where id_reader =?")) {
            con.setAutoCommit(false);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
            preparedStatement2.setInt(1, id);
            preparedStatement2.executeUpdate();
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            response = preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException exec) {
            log.error(exec.getMessage());
            con.rollback();
            response = -1;
        }
        return response;
    }

    @Override
    public int update(Reader r) {
        int response;
        Reader real = get(r.getId()).get();
        try (Connection connection = pool.getConnection()) {
            response = updateQuery(connection, real, r);
        } catch (SQLException e) {
            Logger.getLogger(DaoReaderImpl.class.getName())
                    .log(Level.SEVERE, null, e);
            response = -3;
        }

        return response;
    }

    private int updateQuery(Connection connection, Reader real, Reader r) {
        int response = 1;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "UPDATE reader set name_reader =?, birth_reader=? where id = ?");
             PreparedStatement pS = connection.prepareStatement(
                     "UPDATE  login set username = ?, password=? where id_reader= ?"
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
            if (r.getLogin().getUsername() == null) {
                pS.setString(1, real.getLogin().getUsername());
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
        } catch (SQLException e) {
            Logger.getLogger(DaoReaderImpl.class.getName())
                    .log(Level.SEVERE, null, e);
            response = -3;
        }
        return response;
    }

    @Override
    public int add(Reader r) {
        int response;
        try (Connection con = pool.getConnection()) {
            response = addQuery(con, r);
        } catch (SQLException e) {
            log.error(e.getMessage());
            response = -3;
        }

        return response;
    }

    private int addQuery(Connection con, Reader r) {
        int response;
        try (
                PreparedStatement preparedStatement = con.prepareStatement(
                        "INSERT INTO reader (name_reader, birth_reader) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement pS = con.prepareStatement(
                        "INSERT INTO login (username, password) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            con.setAutoCommit(false);

            preparedStatement.setString(1, r.getName_reader());
            preparedStatement.setDate(2, Date.valueOf(r.getBirth_reader()));
            pS.setString(1, r.getLogin().getUsername());
            pS.setString(2, r.getLogin().getPassword());

            response = preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException exception) {
                log.error(exception.getMessage());
            }
            e.printStackTrace();
            response = -2;
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
                String username = rs.getString("user");
                String password = rs.getString("password");
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
