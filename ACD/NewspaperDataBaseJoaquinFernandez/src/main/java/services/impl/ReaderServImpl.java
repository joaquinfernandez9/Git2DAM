package services.impl;

import dao.*;
import dao.impl.DaoLoginImpl;
import dao.impl.DaoTypeImpl;
import model.Article;
import model.Login;
import model.ReadArticle;
import model.Reader;
import services.ReaderServ;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class ReaderServImpl implements ReaderServ {

    private final DaoNewspaper daoNewspaperImpl;
    private final DaoReader daoReaderImpl;
    private final DaoArticle daoArticleImpl;
    private final DaoTypeImpl daoTypeImpl;

    private final DaoLoginImpl daoLoginImpl;
    private final DaoReadArticle daoReadArticleImpl;
    private final DaoSubscriptions daoSubscriptions;

    @Inject
    public ReaderServImpl(DaoLoginImpl daoLoginImpl, DaoNewspaper daoNewspaperImpl, DaoSubscriptions daoSubscriptions, DaoReader daoReaderImpl, DaoArticle daoArticleImpl, DaoTypeImpl daoTypeImpl, DaoReadArticle daoReadArticleImpl) {
        this.daoNewspaperImpl = daoNewspaperImpl;
        this.daoReaderImpl = daoReaderImpl;
        this.daoArticleImpl = daoArticleImpl;
        this.daoTypeImpl = daoTypeImpl;
        this.daoReadArticleImpl = daoReadArticleImpl;
        this.daoSubscriptions = daoSubscriptions;
        this.daoLoginImpl = daoLoginImpl;
    }

    @Override
    public Either<Integer, List<Reader>> getAll(int idNews, String description) {
        return daoReaderImpl.getAll(idNews, description);
    }

    @Override
    public Either<Integer, Reader> get(int id) {
        return daoReaderImpl.get(id);
    }


    @Override
    public int deleteReader(int idReader) {
        int restult = 0;
        if (daoReaderImpl.get(idReader).isRight()) {
            daoReaderImpl.delete(idReader);
            restult = 1;
        }
        return restult;
    }

    @Override
    public Either<Integer, List<Reader>> getAll(String description) {
        return daoReaderImpl.getAll(description);
    }

    @Override
    public int update(Reader r) {
        return -1;
    }

    @Override
    public int add(Login log) {
        return daoLoginImpl.add(log);
    }

    @Override
    public int appendReadArticle(Reader reader, int article, int rating) {
        int response;
        Article art = daoArticleImpl.getAll().stream().filter(article1 ->
                article1.getId() == article).findFirst().orElse(null);
        if (!daoArticleImpl.getAll().contains(art)) {
            response = -5;
        } else {
            ReadArticle readArticle = new ReadArticle(reader,
                    art, rating);
            daoReadArticleImpl.add(readArticle);
            response = 1;
        }
        return response;
    }

}
