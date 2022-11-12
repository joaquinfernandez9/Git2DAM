package dao;

import io.vavr.control.Either;
import model.Subscription;

import java.util.List;

public interface DaoSubscriptions {
    int delete(int id);

    int add(Subscription subscription);

    Either<Integer, List<Subscription>> getAll();

    Either<Integer, List<Subscription>> getAll(int id);


}
