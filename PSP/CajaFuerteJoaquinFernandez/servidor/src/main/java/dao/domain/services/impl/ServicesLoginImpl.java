package dao.domain.services.impl;

import config.Const;
import dao.DaoLogin;
import domain.User;
import dao.domain.services.ServicesLogin;
import jakarta.inject.Inject;
import jakarta.security.JWTBlackList;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

public class ServicesLoginImpl implements ServicesLogin {
    private DaoLogin daoLogin;


    private final JWTBlackList jwtBlackList;

    @Inject
    public ServicesLoginImpl(DaoLogin daoLogin, JWTBlackList jwtBlackList) {
        this.daoLogin = daoLogin;
        this.jwtBlackList = jwtBlackList;
    }


    @Override public User login(String username, String password) {
        return daoLogin.login(username, password);
    }

    @Override public User register(User usuario) {
        return daoLogin.register(usuario);
    }

    @Override
    public void scLogout(String authorization) {
        String[] headerFields = authorization.split(Const.BLANK);
        if (headerFields.length == 2) {
            String token = headerFields[1];
            jwtBlackList.getJWTBlackList().add(token);
        }
    }

}
