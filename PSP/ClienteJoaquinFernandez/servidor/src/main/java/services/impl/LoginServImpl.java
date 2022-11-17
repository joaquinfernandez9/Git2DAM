package services.impl;

import jakarta.inject.Inject;
import dao.DaoLogin;
import services.LoginServ;

public class LoginServImpl implements LoginServ {

    private final DaoLogin daoLoginImpl;

    @Inject
    public LoginServImpl(DaoLogin daoLoginImpl) {
        this.daoLoginImpl = daoLoginImpl;
    }

    @Override
    public int login(String userName, String password) {
        return daoLoginImpl.login(userName, password);
    }

    @Override
    public int get(String userName, String password){
        return daoLoginImpl.get(userName, password);
    }

}
