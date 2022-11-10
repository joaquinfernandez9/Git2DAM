package dao;

import model.ArticleType;

import java.util.List;

public interface DaoType {
    List<ArticleType> getAll();

    ArticleType get(int id);

    int add(ArticleType a);

    int delete(int id);

    int update(ArticleType type);
}
