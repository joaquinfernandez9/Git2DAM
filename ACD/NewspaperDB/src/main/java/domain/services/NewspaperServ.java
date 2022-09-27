package domain.services;

import dao.DaoNewspaper;
import domain.modelo.Newspaper;
import jakarta.inject.Inject;

import java.util.List;

public class NewspaperServ {

    private final DaoNewspaper daoNewspaper;

    @Inject
    public NewspaperServ(DaoNewspaper daoNewspaper){
        this.daoNewspaper = daoNewspaper;
    }

    public List<Newspaper> getAll(){
        return daoNewspaper.getAll();
    }

    public boolean deleteNewspaper(int news){
        return daoNewspaper.deleteNewspaper(news);
    }

}
