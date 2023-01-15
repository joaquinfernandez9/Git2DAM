package services;

import model.Article;

import java.util.List;

public interface ArticleServ {
    List<Article> getAll();

    List<Article> getAll(int id);

    List<Article> getArticlesOfAReader(int idReader);

    List<Article> getArticlesFilter(String description);

    int addArticle(Article a);

    int deleteArticle(int id);

    int updateArticle(Article a);
}
