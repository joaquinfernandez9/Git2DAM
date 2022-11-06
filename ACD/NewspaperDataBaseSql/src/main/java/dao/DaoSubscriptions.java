package dao;

import io.vavr.control.Either;
import model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface DaoSubscriptions {
    int deleteSubscriptions(int id);

    Either<Integer, List<Subscription>> getAll();

}
