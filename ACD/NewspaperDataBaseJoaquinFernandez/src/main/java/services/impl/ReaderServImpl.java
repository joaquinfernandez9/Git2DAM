package services.impl;

import dao.*;
import dao.impl.DaoLoginImpl;
import dao.impl.DaoTypeImpl;
import model.*;
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
    public Either<Integer, List<Reader>> getAll(Newspaper idNews, ArticleType description) {
        if (idNews.getId() == 0 && description == null) {
            return daoReaderImpl.getAll();
        } else if (idNews.getId() != 0 && description == null) {
            return daoReaderImpl.getAll(idNews);
        } else if (idNews.getId() == 0 && description != null) {
            return daoReaderImpl.getAll(description);
        } else {
            return Either.left(-1);
        }


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
    public int update(Reader r) {
        return -1;
    }

    @Override
    public int add(Login log) {
        return daoLoginImpl.add(log);
    }


}
