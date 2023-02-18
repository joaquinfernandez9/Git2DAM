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

    private final DaoReader daoReaderImpl;

    private final DaoLoginImpl daoLoginImpl;

    @Inject
    public ReaderServImpl(DaoLoginImpl daoLoginImpl, DaoNewspaper daoNewspaperImpl, DaoSubscriptions daoSubscriptions, DaoReader daoReaderImpl, DaoArticle daoArticleImpl, DaoTypeImpl daoTypeImpl, DaoReadArticle daoReadArticleImpl) {
        this.daoReaderImpl = daoReaderImpl;
        this.daoLoginImpl = daoLoginImpl;
    }

    @Override
    public List<Reader> getAll() {
        return daoReaderImpl.getAll();
    }

    @Override
    public List<Reader> getAll(Newspaper np) {
        return daoReaderImpl.getAll(np);
    }

    @Override
    public List<Reader> getAll(String type) {
        return daoReaderImpl.getAll(type);
    }

    @Override
    public Reader get(int id) {
        return daoReaderImpl.get(id);
    }


    @Override
    public int deleteReader(Newspaper np,Reader idReader) {
        int restult = 0;
        if (daoReaderImpl.get(idReader.getId()) != null){
            daoReaderImpl.delete(np, idReader);
            restult = 1;
        }
        return restult;
    }


    @Override
    public int update(Reader r) {
        return daoReaderImpl.update(r);
    }

    @Override
    public int add(Reader r, Newspaper np) {
        return daoReaderImpl.save(r, np);
    }


}
