package domain.services;

import dao.DaoType;
import domain.modelo.ArticleType;
import jakarta.inject.Inject;

import java.util.List;

public class TypeServ {

    private final DaoType daoType;

    @Inject
    public TypeServ(DaoType daoType) {
        this.daoType = daoType;
    }

    public List<ArticleType> getAll(){
        return daoType.getAll();
    }



}
