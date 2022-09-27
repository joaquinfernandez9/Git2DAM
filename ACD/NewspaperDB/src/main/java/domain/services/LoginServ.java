package domain.services;

import dao.DaoLogin;
import jakarta.inject.Inject;

public class LoginServ {

    DaoLogin daoLogin;

    @Inject
    public LoginServ(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    public boolean login(String userName, String password){
        return daoLogin.login(userName, password);
    }

}
