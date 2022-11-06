package dao;

import io.vavr.control.Either;
import model.ArticleType;

import java.util.List;

public interface DaoType {
    List<ArticleType> getAll();

    ArticleType get(Integer id, String description);
}
