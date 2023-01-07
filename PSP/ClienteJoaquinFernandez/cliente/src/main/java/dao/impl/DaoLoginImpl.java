package dao.impl;

import com.google.gson.Gson;
import dao.DaoLogin;
import dao.retrofit.llamadas.LoginApi;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Login;
import okhttp3.Credentials;

@Log4j2
public class DaoLoginImpl extends DaoGeneric implements DaoLogin {

    private final LoginApi api;

    @Inject
    public DaoLoginImpl(Gson gson, LoginApi api) {
        super(gson);
        this.api = api;
    }

    @Override
    public Single<Either<String, Login>> log(Login log){
        String credentials = Credentials.basic(log.getUser(), log.getPassword());
        return safeAPICall(api.login(credentials));
    }

    @Override
    public Single<Either<String, Login>> register(Login log){
        return safeAPICall(api.register(log));
    }


}
