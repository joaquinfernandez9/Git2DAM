package retrofit;

import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginApi {

    @POST("login/log")
    Single<Usuario> log(@Header("Authorization") String auth);
}
