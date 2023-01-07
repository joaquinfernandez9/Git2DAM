package dao;

import model.Login;

public interface DaoLogin {
    int login(String userName, String password);

    int delete(int id);

    int update(Login log);

    int add(Login log);
}
