package services.impl;

import dao.DaoNewspaper;
import model.Newspaper;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import services.NewspaperServ;

import java.util.List;
import java.util.Map;

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
    public int deleteNewspaper(ObjectId id) {
        return daoNewspaper.delete(id);
    }

    @Override
    public Map<String, Integer> getNbrArticles(ObjectId newspaper) {
        return daoNewspaper.getNbrArticles(newspaper);
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
