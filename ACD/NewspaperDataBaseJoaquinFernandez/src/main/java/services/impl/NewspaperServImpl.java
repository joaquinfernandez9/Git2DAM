package services.impl;

import dao.DaoNewspaper;
import model.Newspaper;
import jakarta.inject.Inject;
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
        if (daoNewspaper.get(id).getArticles().isEmpty()) {
            return daoNewspaper.delete(id);
        } else {
            return 0;
        }

    }


    @Override
    public int add(Newspaper n) {
        return daoNewspaper.add(n);
    }

    @Override
    public int update(Newspaper n) {
        return daoNewspaper.update(n);
    }


}
