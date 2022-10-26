package dao;

import model.Article;
import io.vavr.control.Either;

import java.util.List;

public interface DaoArticle {
    List<Article> getAll();

    void save(Article a);

    Either<String, Boolean> delete(int id);
}
