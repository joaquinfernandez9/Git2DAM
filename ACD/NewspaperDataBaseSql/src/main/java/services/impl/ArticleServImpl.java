package services.impl;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.DaoType;
import model.Article;
import model.ArticleType;
import model.Newspaper;
import services.ArticleServ;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServImpl implements ArticleServ {

    private final DaoArticle daoArticleImpl;
    private final DaoType daoTypeImpl;
    private final DaoNewspaper daoNewspaperImpl;

    @Inject
    public ArticleServImpl(DaoArticle daoArticleImpl, DaoType daoTypeImpl,
                       DaoNewspaper daoNewspaperImpl) {
        this.daoArticleImpl = daoArticleImpl;
        this.daoTypeImpl = daoTypeImpl;
        this.daoNewspaperImpl = daoNewspaperImpl;
    }

    @Override public List<Article> getAll() {
        return daoArticleImpl.getAll();
    }

    @Override public List<Article> getArticlesFilter(String description) {
        List<Article> articlesList = getAll();
        ArticleType articleTypes = daoTypeImpl.get(null, description);
        if (articleTypes == null) {
            return Collections.emptyList();
        } else {
            return articlesList.stream()
                    .filter(article ->
                            article.getTypeID() == articleTypes.getTypeID())
                    .collect(Collectors.toList());
        }
    }

    @Override public void addArticle(Article a) {
        List<Article> articles = getAll();
        ArticleType art = daoTypeImpl.get(a.getTypeID(), null);
        Newspaper np = daoNewspaperImpl.get(a.getNewspaperID());
        if (!articles.contains(a) && np != null && art != null) {
            daoArticleImpl.save(a);
        }
    }

}


