package dao;

import model.Article;

import java.util.List;

public interface DaoArticle {
    List<Article> getAll();

    List<Article> getAll(int newspaperId);



    int save(Article a);

    int delete(int id);

}
