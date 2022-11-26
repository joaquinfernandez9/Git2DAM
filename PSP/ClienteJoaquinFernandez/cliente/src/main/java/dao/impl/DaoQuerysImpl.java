package dao.impl;

import com.google.gson.Gson;
import dao.DaoQuerys;
import dao.retrofit.llamadas.QuerysApi;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Reader;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import model.querys.QueryDescNumber;

import java.util.List;

public class DaoQuerysImpl extends DaoGeneric implements DaoQuerys {
    private final QuerysApi querysApi;

    @Inject
    public DaoQuerysImpl(Gson gson, QuerysApi querysApi) {
        super(gson);
        this.querysApi = querysApi;
    }

    @Override
    public Single<Either<String, List<QueryDescNumber>>> getQuery1() {
        return safeAPICall(querysApi.getQuery1());
    }

    @Override
    public Single<Either<String, List<Reader>>> getQuery2(int id) {
        return safeAPICall(querysApi.getQuery2(id));
    }

    @Override
    public Single<Either<String, List<QueryArticlesNewspaper>>> getQuery3(String desc) {
        return safeAPICall(querysApi.getQuery3(desc));
    }

    @Override
    public Single<Either<String, List<QueryArticleRating>>> getQuery4(int id) {
        return safeAPICall(querysApi.getQuery4(id));
    }
}
