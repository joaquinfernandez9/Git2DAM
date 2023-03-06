package retrofit;

import domain.modelo.Informe;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface InformesApi {

    @GET("informes")
    Single<List<Informe>> getAll();

    @GET("informes/{informe}")
    Single<Informe> get(@Path("informe") String nombreInforme);

    @POST("informes/add")
    Single<Informe> add(@Body Informe informe);




}
