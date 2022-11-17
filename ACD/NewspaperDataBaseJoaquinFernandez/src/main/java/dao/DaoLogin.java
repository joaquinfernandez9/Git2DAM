package dao;

public interface DaoLogin {
    int login(String userName, String password);

    int get(String userName, String password);
}
