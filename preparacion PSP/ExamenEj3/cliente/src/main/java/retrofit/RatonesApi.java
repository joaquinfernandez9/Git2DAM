package retrofit;

import domain.modelo.Raton;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface RatonesApi {
    @GET("ratones")
    Single<List<Raton>> getAll();

    @POST("ratones")
    Single<Raton> add(@Body Raton raton);
}
