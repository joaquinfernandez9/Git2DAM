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

    public ArticleType getFilter(String descricpion) {
        List<ArticleType> articleTypes = getAll();
        return articleTypes.stream()
                .filter(linea -> linea.getDescription().equals(descricpion))
                .findFirst().orElse(null);
    }

    public ArticleType getByID(int id){
        List<ArticleType> types = getAll();
        return types.stream()
                .filter(type ->
                        type.getTypeID() == id)
                .findFirst().orElse(null);
    }

}
