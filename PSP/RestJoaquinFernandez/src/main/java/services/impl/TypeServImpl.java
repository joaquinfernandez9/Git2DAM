package services.impl;

import jakarta.inject.Inject;
import dao.DaoType;
import model.ArticleType;
import services.TypeServ;

import java.util.List;

public class TypeServImpl implements TypeServ {

    private final DaoType daoTypeImpl;

    @Inject
    public TypeServImpl(DaoType daoTypeImpl) {
        this.daoTypeImpl = daoTypeImpl;
    }

    @Override public List<ArticleType> getAll(){
        return daoTypeImpl.getAll();
    }

}
