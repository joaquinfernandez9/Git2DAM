package dao.impl;

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
    public int deleteSubscriptions(int id) {
        int response = 0;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("delete from subscribe where id_reader=?")) {
            preparedStatement.setInt(1, id);
            response = preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return response;
    }

    public int add(Subscription subscription) {
        int response;
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement("insert into subscribe (id_newspaper, id_reader, sing_date) values (?,?,?)");) {
            ps.setInt(1, subscription.getIdNewspaper());
            ps.setInt(2, subscription.getIdReader());
            LocalDate date = LocalDate.now();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            ps.setDate(3, Date.valueOf(dateFormat.format(date)));
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
            ResultSet rs = statement.executeQuery("select * from subscribe");

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
                int id_newspaper = rs.getInt("id_newspaper");
                int id_reader = rs.getInt("id_reader");
                Subscription subscription = new Subscription(id_reader, id_newspaper);
                result.add(subscription);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }

}
