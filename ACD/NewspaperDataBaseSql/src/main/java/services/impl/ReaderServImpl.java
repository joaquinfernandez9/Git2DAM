package services.impl;

import dao.*;
import model.Article;
import model.ReadArticle;
import model.Reader;
import services.ReaderServ;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

public class ReaderServImpl implements ReaderServ {

    private final DaoNewspaper daoNewspaperImpl;
    private final DaoReader daoReaderImpl;
    private final DaoArticle daoArticleImpl;
    private final DaoType daoTypeImpl;
    private final DaoReadArticle daoReadArticleImpl;
    private final DaoSubscriptions daoSubscriptions;

    @Inject
    public ReaderServImpl(DaoNewspaper daoNewspaperImpl, DaoSubscriptions daoSubscriptions, DaoReader daoReaderImpl, DaoArticle daoArticleImpl, DaoType daoTypeImpl, DaoReadArticle daoReadArticleImpl) {
        this.daoNewspaperImpl = daoNewspaperImpl;
        this.daoReaderImpl = daoReaderImpl;
        this.daoArticleImpl = daoArticleImpl;
        this.daoTypeImpl = daoTypeImpl;
        this.daoReadArticleImpl = daoReadArticleImpl;
        this.daoSubscriptions = daoSubscriptions;
    }

    @Override
    public Either<Integer, List<Reader>> getAll(int idNews, int num, String description) {
        return daoReaderImpl.getAll(idNews, num, description);
    }

    @Override
    public Either<Integer, Reader> get(int id) {
        return daoReaderImpl.get(id);
    }


    @Override
    public int deleteReader(int idReader) {
        int restult = 0;
        if (daoReaderImpl.get(idReader).isRight()) {
            daoReadArticleImpl.delete(idReader);
            daoSubscriptions.deleteSubscriptions(idReader);
            daoReaderImpl.delete(idReader);
            restult = 1;
        }


        return restult;
    }

    @Override
    public int update(Reader r) {
        return daoReaderImpl.update(r);
    }

    @Override
    public int add(Reader r) {
        return daoReaderImpl.add(r);
    }

    @Override
    public int appendReadArticle(Reader reader, int article, int rating) {
        int response;
        if (!daoArticleImpl.getAll().contains(article)) {
            //not found
            response = -5;
        } else {
            ReadArticle readArticle = new ReadArticle(reader.getId(),
                    article, rating);
            daoReadArticleImpl.add(readArticle);
            response = 1;
        }
        return response;
    }

}