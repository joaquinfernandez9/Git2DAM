package services.impl;

import jakarta.inject.Inject;
import dao.DaoNewspaper;
import model.Newspaper;
import services.NewspaperServ;

import java.util.List;

public class NewspaperServImpl implements NewspaperServ {

    private final DaoNewspaper daoNewspaper;

    @Inject
    public NewspaperServImpl(DaoNewspaper daoNewspaper) {
        this.daoNewspaper = daoNewspaper;
    }

    @Override
    public List<Newspaper> getAll() {
        return daoNewspaper.getAll();
    }


    @Override
    public int deleteNewspaper(int id) {
        return daoNewspaper.delete(id);
    }


    @Override
    public int add(Newspaper n) {
        return daoNewspaper.add(n);
    }

    @Override
    public int update(Newspaper n) {
        return daoNewspaper.update(n);
    }

    @Override
    public Newspaper get(int id) {
        return daoNewspaper.get(id);
    }


}
