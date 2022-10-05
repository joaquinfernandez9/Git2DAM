package domain.services;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.DaoType;
import domain.modelo.Article;
import domain.modelo.ArticleType;
import domain.modelo.Newspaper;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServ {

    private final DaoArticle daoArticle;
    private final DaoType daoType;
    private final DaoNewspaper daoNewspaper;

    @Inject
    public ArticleServ(DaoArticle daoArticle, DaoType daoType,
                       DaoNewspaper daoNewspaper) {
        this.daoArticle = daoArticle;
        this.daoType = daoType;
        this.daoNewspaper = daoNewspaper;
    }



    public List<Article> getAll() {
        return daoArticle.getAll();
    }

    public List<Article> getArticlesFilter(String description) {
        List<Article> articlesList = getAll();
        ArticleType articleTypes = daoType.get(null, description);
        if (articleTypes == null) {
            return Collections.emptyList();
        } else {
            return articlesList.stream()
                    .filter(article ->
                            article.getTypeID() == articleTypes.getTypeID())
                    .collect(Collectors.toList());
        }
    }

    public void addArticle(Article a) {
        List<Article> articles = getAll();
        ArticleType art = daoType.get(a.getTypeID(), null);
        Newspaper np = daoNewspaper.get(a.getNewspaperID());
        if (!articles.contains(a) && np != null && art != null) {
            daoArticle.save(a);
        }
    }

}


