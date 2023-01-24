package services;

import model.Article;
import model.ArticleType;

import java.util.List;
import java.util.Map;

public interface ArticleServ {
    List<Article> getAll();

    List<Article> getAll(int id);

    List<Article> getArticlesOfAReader(int idReader);

    List<Article> getArticlesFilter(String description);

    int addArticle(Article a);

    String deleteArticle(int id);



    int updateArticle(Article a);
}
