package dao.impl;

import dao.Const;
import dao.DaoSubscriptions;
import dao.dataBase.DataBaseConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Subscription;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoSubscriptionsImpl implements DaoSubscriptions {


    private final DataBaseConnectionPool db;

    @Inject
    public DaoSubscriptionsImpl(DataBaseConnectionPool db) {
        this.db = db;
    }

    @Override
    public int delete(int idReader, int idNewspaper) {
        int response = 0;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement =
                     con.prepareStatement(Const.DELETE_FROM_SUBSCRIBE_WHERE_ID_READER);
             PreparedStatement ps =
                con.prepareStatement(Const.DELETE_FROM_SUBSCRIBE_WHERE_ID_READER_AND_ID_NEWSPAPER);
             PreparedStatement deleteReadArticles =
                con.prepareStatement(Const.DELETE_FROM_READ_ARTICLES_WHERE_ID_READER)) {
            con.setAutoCommit(false);
            preparedStatement.setInt(1, idReader);
            ps.setInt(1, idReader);
            if (idNewspaper == 0){
                response = preparedStatement.executeUpdate();
            } else {
                ps.setInt(2, idNewspaper);
                response = ps.executeUpdate();
            }
            con.commit();
        } catch (SQLException sqle) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return response;
    }

    public int add(Subscription subscription) {
        int response;
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(Const.addSubscribe);) {
            ps.setInt(1, subscription.getId_newspaper());
            ps.setInt(2, subscription.getId_reader());
            LocalDate date = LocalDate.now();
            ps.setDate(3, Date.valueOf(date));
            ps.setDate(4, null);
            response = ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            response = -3;
        }
        return response;
    }

    @Override
    public Either<Integer, List<Subscription>> getAll() {
        Either<Integer, List<Subscription>> result;
        Connection con = db.getConnection();
        try (con; Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = statement.executeQuery(Const.getAllFromSubscribe);
            if (rs != null) {
                result = Either.right(readRS(rs));
            } else {
                result = Either.left(-2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = Either.left(-3);
        }
        return result;
    }

    @Override
    public Either<Integer, List<Subscription>> getAll(int id) {
        Either<Integer, List<Subscription>> result;
        Connection con = db.getConnection();
        try (con; PreparedStatement ps = con.prepareStatement(Const.getAllSubscriptionsOfReader)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                result = Either.right(readRS(rs));
            } else {
                result = Either.left(-2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = Either.left(-3);
        }
        return result;
    }


    private List<Subscription> readRS(ResultSet rs) {
        List<Subscription> result = new ArrayList<>();
        try {
            while (rs.next()) {
                int id_newspaper = rs.getInt(Const.ID_NEWSPAPER);
                int id_reader = rs.getInt(Const.ID_READER);
                LocalDate sing_date = rs.getDate(Const.SING_DATE).toLocalDate();
                Date cancellation_date = rs.getDate(Const.CANCELLATION_DATE);
                Subscription subscription;
                if (cancellation_date != null) {
                    subscription = new Subscription(id_reader, id_newspaper,
                            sing_date, cancellation_date.toLocalDate());
                } else {
                    subscription = new Subscription(id_reader, id_newspaper, sing_date);
                }
                result.add(subscription);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }


}
