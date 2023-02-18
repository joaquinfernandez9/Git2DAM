package services.impl;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.DaoType;
import model.Article;
import model.ArticleType;
import model.Newspaper;
import org.bson.types.ObjectId;
import services.ArticleServ;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArticleServImpl implements ArticleServ {

    private final DaoArticle daoArticleImpl;

    @Inject
    public ArticleServImpl(DaoArticle daoArticleImpl) {
        this.daoArticleImpl = daoArticleImpl;
    }

    @Override
    public List<Article> getAll() {
        return daoArticleImpl.getAll();
    }



    @Override
    public List<Article> getAll(ObjectId id) {
        return daoArticleImpl.getAll(id);
    }

    @Override
    public List<Article> getArticlesOfAReader(int idReader) {
        return daoArticleImpl.getAll();
    }

    //show articles by type
    @Override
    public List<Article> getArticlesFilter(String description) {
        return null;
    }

    @Override
    public int addArticle(Article a, Newspaper np) {
        return daoArticleImpl.save(a,np);
    }

    @Override
    public int deleteArticle(Article id, Newspaper np) {
        return daoArticleImpl.delete(id, np);
    }

    @Override
    public List<Article> deleteArticle(ObjectId id) {
        return daoArticleImpl.delete(id);
    }


    @Override
    public int updateArticle(Article article, Newspaper newspaper, String desc) {
        return daoArticleImpl.update(article, newspaper, desc);
    }


}


