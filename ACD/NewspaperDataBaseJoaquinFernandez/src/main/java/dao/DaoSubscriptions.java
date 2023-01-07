package dao;

import io.vavr.control.Either;
import model.Subscription;

import java.util.List;

public interface DaoSubscriptions {
    int delete(int id, int idNewspaper);

    int add(Subscription subscription);

    Either<Object, List<Subscription>> getAll();

    Either<Object, List<Subscription>> getAll(int id);


}
