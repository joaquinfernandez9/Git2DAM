package dao;

import domain.User;

public interface DaoLogin {
    User login(String username, String password);

    User register(User usuario);

    boolean correctPass(String password, String user);
}
