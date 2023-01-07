package dao.retrofit.llamadas;

import dao.Common;
import io.reactivex.rxjava3.core.Single;
import model.Newspaper;
import retrofit2.Response;
import retrofit2.http.*;
import java.util.List;

public interface NewspaperApi {
    @GET(Common.NEWSPAPERS)
    Single<List<Newspaper>> getNewspapers();

    @GET(Common.NEWSPAPERS + Common.ID_PARAM)
    Single<Newspaper> getNewspaperById(@Path(Common.ID) int id);

    @POST(Common.NEWSPAPERS)
    Single<Newspaper> saveNewspaper(@Body Newspaper newspaper);

    @PUT(Common.NEWSPAPERS)
    Single<Newspaper> updateNewspaper(@Body Newspaper newspaper);

    @DELETE(Common.NEWSPAPERS + Common.ID_PARAM)
    Single<Response<Object>> deleteNewspaper(@Path(Common.ID) int id);

    @DELETE(Common.NEWSPAPERS +"confirm/"+ Common.ID_PARAM)
    Single<Response<Object>> deleteConfirmed(@Path(Common.ID) int id);

}
