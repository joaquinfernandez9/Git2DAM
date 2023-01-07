package dao;

import model.ArticleType;

import java.util.List;

public interface DaoType {
    List<ArticleType> getAll();

    ArticleType get(Integer id);

}
