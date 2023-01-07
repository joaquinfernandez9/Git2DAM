package domain.services.impl;

import domain.services.LoginServ;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import dao.DaoLogin;
import model.Code;
import model.Login;

public class LoginServImpl implements LoginServ {

    private final DaoLogin daoLoginImpl;

    @Inject
    public LoginServImpl(DaoLogin daoLoginImpl) {
        this.daoLoginImpl = daoLoginImpl;
    }

    @Override
    public Either<String, Login> login(String userName, String password) {
        return daoLoginImpl.login(userName, password);
    }

    @Override
    public Either<String, Login> register(Login log, Code code){
        return daoLoginImpl.register(log, code);
    }


    @Override
    public Either<String, Code> activarCuenta(Code code) {
        return daoLoginImpl.activarCuenta(code);
    }

    @Override
    public Either<String, Login> recuperarContrasena(Login log){
        return daoLoginImpl.recuperarContrasena(log);
    }


}
