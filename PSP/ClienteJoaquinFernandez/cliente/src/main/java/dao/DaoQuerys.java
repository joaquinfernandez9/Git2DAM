package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Reader;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import model.querys.QueryDescNumber;

import java.util.List;

public interface DaoQuerys {
    Single<Either<String, List<QueryDescNumber>>> getQuery1();

    Single<Either<String, List<Reader>>> getQuery2(int id);

    Single<Either<String, List<QueryArticlesNewspaper>>> getQuery3(String desc);

    Single<Either<String, List<QueryArticleRating>>> getQuery4(int id);
}
