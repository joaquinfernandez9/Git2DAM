package services.impl;

import dao.DaoLogin;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Login;
import services.LoginServ;

public class LoginServImpl implements LoginServ {

    private final DaoLogin dao;

    @Inject
    public LoginServImpl(DaoLogin dao) {
        this.dao = dao;
    }

    @Override
    public Single<Either<String, Login>> log(Login log){
        return dao.log(log);
    }

    @Override
    public Single<Either<String, Login>> register(Login log){
        return dao.register(log);
    }

}
