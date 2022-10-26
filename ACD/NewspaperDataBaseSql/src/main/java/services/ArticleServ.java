package services;

import model.Article;

import java.util.List;

public interface ArticleServ {
    List<Article> getAll();

    List<Article> getArticlesFilter(String description);

    void addArticle(Article a);
}
