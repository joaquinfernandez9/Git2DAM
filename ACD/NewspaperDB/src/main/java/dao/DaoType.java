package dao;

import domain.modelo.ArticleType;

import java.util.List;

public interface DaoType {
    List<ArticleType> getAll();

    ArticleType get(Integer id, String description);
}
