package dao;

import io.vavr.control.Either;
import model.ReadArticle;

import java.sql.SQLException;
import java.util.List;

public interface DaoReadArticle {
    Either<Integer, List<ReadArticle>> getAll();

    int delete(int id);

    int add(ReadArticle readArticle);
}