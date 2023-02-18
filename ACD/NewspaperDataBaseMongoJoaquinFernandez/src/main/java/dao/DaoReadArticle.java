package dao;

import io.vavr.control.Either;
import model.ReadArticle;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DaoReadArticle {
    Either<Integer, List<ReadArticle>> getAll();

    Map<Double, String> getAvgRating(int reader);
    int add(ReadArticle readArticle);
}
