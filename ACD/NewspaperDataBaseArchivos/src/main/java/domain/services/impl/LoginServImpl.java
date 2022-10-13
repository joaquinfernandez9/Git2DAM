package domain.services.impl;

import dao.DaoLogin;
import domain.services.LoginServ;
import jakarta.inject.Inject;

public class LoginServImpl implements LoginServ {

    private final DaoLogin daoLoginImpl;

    @Inject
    public LoginServImpl(DaoLogin daoLoginImpl) {
        this.daoLoginImpl = daoLoginImpl;
    }

    @Override public boolean login(String userName, String password){
        return daoLoginImpl.login(userName, password);
    }

}
