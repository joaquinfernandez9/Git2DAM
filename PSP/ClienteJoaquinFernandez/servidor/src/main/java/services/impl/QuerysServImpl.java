package services.impl;

import dao.DaoQuerys;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.querys.QueryArticleRating;
import model.querys.QueryDescNumber;
import model.Reader;
import model.querys.QueryArticlesNewspaper;
import services.QuerysServ;

import java.util.List;

public class QuerysServImpl implements QuerysServ {
    
    private final DaoQuerys querys;

    @Inject
    public QuerysServImpl(DaoQuerys querys) {
        this.querys = querys;
    }
    
    @Override public Either<Integer, List<QueryDescNumber>> getAll(){
        return querys.getAll();
    }
    
    @Override public Either<Integer, List<Reader>> getOldest(int idNewspaper){
        return querys.getOldest(idNewspaper);
    }
    
    
    @Override public List<QueryArticlesNewspaper> getAll(String description){
        return querys.getAll(description);
    }
    
    @Override public List<QueryArticleRating> getArticles(int idNewspaper){
        return querys.getArticles(idNewspaper);
    }
}
