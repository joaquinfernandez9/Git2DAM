package services.impl;

import dao.DaoReadArticle;
import jakarta.inject.Inject;
import model.Article;
import model.ReadArticle;
import services.ReadArticleServ;

import java.util.List;
import java.util.Map;

public class ReadArticleServImpl implements ReadArticleServ {

    private final DaoReadArticle daoReadArticle;

    @Inject
    public ReadArticleServImpl(DaoReadArticle daoReadArticle) {
        this.daoReadArticle = daoReadArticle;
    }

    @Override
    public List<ReadArticle> getAll(int id) {
        return daoReadArticle.getAll().get();
    }

    @Override
    public int appendReadArticle(ReadArticle readArticle) {
        return daoReadArticle.add(readArticle);
    }

    @Override
    public Map<Double, String> getAvgRating(int reader) {
        return daoReadArticle.getAvgRating(reader);
    }



}
