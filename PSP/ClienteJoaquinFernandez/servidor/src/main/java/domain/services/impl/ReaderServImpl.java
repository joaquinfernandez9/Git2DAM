package domain.services.impl;

import dao.*;
import dao.impl.DaoTypeImpl;
import domain.services.ReaderServ;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;
import model.ReadArticle;
import model.Reader;

import java.util.List;

public class ReaderServImpl implements ReaderServ {

    private final DaoNewspaper daoNewspaperImpl;
    private final DaoReader daoReaderImpl;
    private final DaoArticle daoArticleImpl;
    private final DaoTypeImpl daoTypeImpl;
    private final DaoReadArticle daoReadArticleImpl;
    private final DaoSubscriptions daoSubscriptions;

    @Inject
    public ReaderServImpl(DaoNewspaper daoNewspaperImpl, DaoSubscriptions daoSubscriptions, DaoReader daoReaderImpl, DaoArticle daoArticleImpl, DaoTypeImpl daoTypeImpl, DaoReadArticle daoReadArticleImpl) {
        this.daoNewspaperImpl = daoNewspaperImpl;
        this.daoReaderImpl = daoReaderImpl;
        this.daoArticleImpl = daoArticleImpl;
        this.daoTypeImpl = daoTypeImpl;
        this.daoReadArticleImpl = daoReadArticleImpl;
        this.daoSubscriptions = daoSubscriptions;
    }

    @Override
    public List<Reader> getAll(int idNews, String description) {
        return daoReaderImpl.getAll(idNews, description);
    }

    @Override
    public Reader get(int id) {
        return daoReaderImpl.get(id);
    }


    @Override
    public void deleteReader(int idReader) {
        Reader reader = daoReaderImpl.get(idReader);
        if (reader != null) {
            daoReaderImpl.delete(idReader);
        }
    }

    @Override
    public Reader update(Reader r) {
        return daoReaderImpl.update(r);
    }

    @Override
    public Reader add(Reader r) {
        return daoReaderImpl.add(r);
    }

    @Override
    public int appendReadArticle(Reader reader, int article, int rating) {
        int response;
        Article art = daoArticleImpl.getAll().stream().filter(article1 ->
                article1.getId() == article).findFirst().orElse(null);
        if (!daoArticleImpl.getAll().contains(art)) {
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
