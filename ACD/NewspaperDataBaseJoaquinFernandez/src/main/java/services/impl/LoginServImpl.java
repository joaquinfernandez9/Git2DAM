package services.impl;

import dao.DaoLogin;
import services.LoginServ;
import jakarta.inject.Inject;

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
        return -1;
    }

}
