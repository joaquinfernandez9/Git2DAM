package domain.services;

import domain.modelo.Article;

import java.util.List;

public interface ArticleServ {
    List<Article> getAll();

    List<Article> getArticlesFilter(String description);

    void addArticle(Article a);
}
