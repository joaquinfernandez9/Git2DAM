package dao;

import domain.modelo.Newspaper;
import io.vavr.control.Either;

import java.util.List;

public interface DaoNewspaper {
    List<Newspaper> getAll();

    Either<String, Boolean> delete(int id);

    Newspaper get(int id);
}
