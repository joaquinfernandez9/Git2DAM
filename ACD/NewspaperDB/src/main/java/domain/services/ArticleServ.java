package domain.services;

import dao.DaoArticle;
import domain.modelo.Article;
import jakarta.inject.Inject;

import java.util.List;

public class ArticleServ {

    private final DaoArticle daoArticle;

    @Inject

    public ArticleServ(DaoArticle daoArticle) {
        this.daoArticle = daoArticle;
    }

    public List<Article> getAll(){
        return daoArticle.getAll();
    }

    public List<Article> getArticlesFilter(String description){
        return daoArticle.getArticlesFilter(description);
    }


}
