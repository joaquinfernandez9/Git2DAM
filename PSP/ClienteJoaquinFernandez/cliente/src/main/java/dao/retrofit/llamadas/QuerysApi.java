package dao.retrofit.llamadas;

import dao.Common;
import io.reactivex.rxjava3.core.Single;
import model.Reader;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import model.querys.QueryDescNumber;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface QuerysApi {

    @GET(Common.QUERYS_QUERY_1)
    Single<List<QueryDescNumber>> getQuery1();

    @GET(Common.QUERYS_QUERY_2_ID)
    Single<List<Reader>> getQuery2(@Path(Common.ID) int id);

    @GET(Common.QUERYS_QUERY_3_DESCRIPTION)
    Single<List<QueryArticlesNewspaper>> getQuery3(@Path(Common.DESCRIPTION) String description);

    @GET(Common.QUERYS_QUERY_4_ID)
    Single<List<QueryArticleRating>> getQuery4(@Path(Common.ID) int id);

}
