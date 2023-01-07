package dao.retrofit.llamadas;

import io.reactivex.rxjava3.core.Single;
import model.Login;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginApi {
    @GET("/private/login")
    Single<Login> login(@Header("auth") String credentials);

    @POST("/private/register")
    Single<Login> register(@Body Login login);

}
