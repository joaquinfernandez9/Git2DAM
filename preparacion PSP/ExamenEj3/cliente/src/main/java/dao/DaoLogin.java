package dao;

import com.google.gson.Gson;
import domain.model.CacheAuth;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import okhttp3.Credentials;
import retrofit.LoginApi;

public class DaoLogin extends DaoGeneric{

    private final LoginApi api;
    private final CacheAuth auth;

    @Inject
    public DaoLogin(Gson gson, LoginApi api, CacheAuth auth) {
        super(gson);
        this.api = api;
        this.auth = auth;
    }




    public Single<Either<String, Usuario>> login(String user, String password){
        auth.setUser(user);
        auth.setPass(password);
        return safeApiCall(api.log(Credentials.basic(user, password)));
    }
}
