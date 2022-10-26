package dao.impl;

import dao.DaoReadArticle;
import dao.DaoReader;
import dao.DaoSubscriptions;
import dao.dataBase.DaoDB;
import dao.dataBase.DataBaseConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Login;
import model.ReadArticle;
import model.Reader;
import model.Subscription;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoReaderImpl implements DaoReader {

    private final DaoDB db;
    private final DataBaseConnectionPool pool;
    private final DaoSubscriptions daoSubscriptions;
    private final DaoReadArticle daoReadArticle;

    @Inject
    public DaoReaderImpl(DaoDB db, DataBaseConnectionPool pool, DaoSubscriptions daoSubscriptions, DaoReadArticle daoReadArticle) {
        this.db = db;
        this.pool = pool;
        this.daoSubscriptions = daoSubscriptions;
        this.daoReadArticle = daoReadArticle;
    }

    @Override
    public Either<Integer, List<Reader>> getAll(int idNews, int num) {
        Either<Integer, List<Reader>> response;
        try (Connection con = pool.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            if (idNews == -1) {
                ResultSet rs = statement.executeQuery("select * from reader where id_reader> 0");
                response = Either.right(readRS(rs));
            } else if (num == -1) {
                PreparedStatement pS = con.prepareStatement(
                        "select reader.id, name_reader, birth_reader\n" +
                                "from reader,\n" +
                                "     newspaper,\n" +
                                "     subscribe\n" +
                                "where reader.id = subscribe.id_reader\n" +
                                "  and newspaper.id = subscribe.id_newspaper\n" +
                                "    and newspaper.id = ?" +
                                "and id_reader> 0");
                pS.setInt(1, idNews);
                ResultSet rs = pS.executeQuery();
                response = Either.right(readRS(rs));
            } else {
                PreparedStatement pS = con.prepareStatement("select name_reader\n" +
                        "from reader,\n" +
                        "     subscribe,\n" +
                        "     newspaper\n" +
                        "where newspaper.id = subscribe.id_newspaper\n" +
                        "  and subscribe.id_reader = reader.id\n" +
                        "  and newspaper.id = ?\n" +
                        "and subscribe.sing_date is not null\n" +
                        "and subscribe.sing_date in\n" +
                        "    (select min(subscribe.sing_date) from subscribe)\n" +
                        "and id_reader > 0" +
                        "LIMIT 5");
                pS.setInt(1, idNews);
                ResultSet rs = pS.executeQuery();
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
                     "select * from reader where id= ?")) {

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
    //deleteByID
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
        try (
                PreparedStatement preparedStatement = con.prepareStatement("delete from reader where id=?");
                PreparedStatement preparedStatement1 = con.prepareStatement("delete from readarticle where id_reader =?");
                PreparedStatement preparedStatement2 = con.prepareStatement("delete from subscribe where id_reader =?")
        ) {
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
        int response = 1;
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
        try (
                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                "UPDATE reader set name_reader =?, birth_reader=? where id = ?");
                PreparedStatement pS = connection.prepareStatement(
                        "UPDATE  login set username = ?, password=? where id_reader= ?"
                )) {
            //reader
            if (r.getDateOfBirth() == null) {
                preparedStatement.setDate(2, Date.valueOf(real.getDateOfBirth()));
            } else {
                preparedStatement.setDate(2, Date.valueOf(r.getDateOfBirth()));
            }
            if (r.getName() == null) {
                preparedStatement.setString(1, real.getName());
            } else {
                preparedStatement.setString(1, r.getName());
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

    private int addQuery(Connection con, Reader r) throws SQLException {
        int response;
        try (
                PreparedStatement preparedStatement = con.prepareStatement(
                        "INSERT INTO reader (name_reader, birth_reader) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement pS = con.prepareStatement(
                        "INSERT INTO login (username, password) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS);) {

            con.setAutoCommit(false);

            preparedStatement.setString(1, r.getName());
            preparedStatement.setDate(2, Date.valueOf(r.getDateOfBirth()));
            pS.setString(1, r.getLogin().getUsername());
            pS.setString(2, r.getLogin().getPassword());

            response = preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
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
                Reader r = new Reader(id, name, date);
                response.add(r);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }


}
