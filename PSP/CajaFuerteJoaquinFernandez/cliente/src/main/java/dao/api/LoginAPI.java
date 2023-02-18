package dao.api;

import domain.User;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginAPI {
    @POST("credentials/login")
    Single<User> login(@Header("Authorization") String authorization);

    @POST("credentials/login")
    Single<User> register(@Body User user);

    @GET("credentials/logout/")
    Single<User> logout(@Header("Authorization") String authorization);
}
