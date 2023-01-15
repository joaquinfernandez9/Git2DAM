package dao;

import model.Article;

import java.util.List;

public interface DaoArticle {
    List<Article> getAll();




    int save(Article a);

    int delete(int id);

    int update(Article a);

}
