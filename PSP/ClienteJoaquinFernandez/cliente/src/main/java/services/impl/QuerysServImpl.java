package services.impl;

import dao.DaoQuerys;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Reader;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import model.querys.QueryDescNumber;
import services.QuerysServ;

import java.util.List;

public class QuerysServImpl implements QuerysServ {
    private final DaoQuerys daoQuerys;

    @Inject
    public QuerysServImpl(DaoQuerys daoQuerys) {
        this.daoQuerys = daoQuerys;
    }

    @Override public Single<Either<String, List<QueryDescNumber>>> getQuery1() {
        return daoQuerys.getQuery1();
    }

    @Override public Single<Either<String, List<Reader>>> getQuery2(int id) {
        return daoQuerys.getQuery2(id);
    }

    @Override public Single<Either<String, List<QueryArticlesNewspaper>>> getQuery3(String desc) {
        return daoQuerys.getQuery3(desc);
    }

    @Override public Single<Either<String, List<QueryArticleRating>>> getQuery4(int id) {
        return daoQuerys.getQuery4(id);
    }

}
