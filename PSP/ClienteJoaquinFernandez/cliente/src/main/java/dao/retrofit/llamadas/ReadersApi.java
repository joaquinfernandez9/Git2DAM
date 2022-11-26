package dao.retrofit.llamadas;

import dao.Common;
import io.reactivex.rxjava3.core.Single;
import model.Reader;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface ReadersApi {

    @GET(Common.READERS)
    Single<List<Reader>> getReaders();

    @GET(Common.READERS + Common.ID_PARAM)
    Single<Reader> getReaderById(@Path(Common.ID) int id);

    @DELETE(Common.READERS + Common.ID_PARAM)
    Single<Response<Object>> deleteReader(@Path(Common.ID) int id);

    @POST(Common.READERS)
    Single<Reader> addReader(@Body Reader reader);

    @PUT(Common.READERS)
    Single<Reader> updateReader(@Body Reader reader);

}
