package domain.services.impl;

import domain.services.NewspaperServ;
import jakarta.inject.Inject;
import dao.DaoNewspaper;
import model.Newspaper;

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
    public void deleteNewspaper(int id) {
        daoNewspaper.delete(id);
    }


    @Override
    public void deleteConfirmed(int id) {
        daoNewspaper.deleteConfirmed(id);
    }


    @Override
    public Newspaper add(Newspaper n) {
        return daoNewspaper.add(n);
    }

    @Override
    public Newspaper update(Newspaper n) {
        return daoNewspaper.update(n);
    }

    @Override
    public Newspaper get(int id) {
        return daoNewspaper.get(id);
    }


}
