package dao;

import domain.modelo.ReadArticle;
import io.vavr.control.Either;

import java.util.List;

public interface DaoReadArticle {
//    Either<String, Boolean> add(int idReader, ReadArticle readArticle);
    List<ReadArticle> getReadArticles(int id);

    int deleteReader(int id);
}
