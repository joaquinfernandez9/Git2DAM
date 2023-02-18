package dao.domain.services;

import domain.User;

public interface ServicesLogin {
    User login(String username, String password);

    User register(User usuario);

    void scLogout(String authorization);
}
