package domain.services;

public interface LoginServ {
    int login(String userName, String password);

    int get(String userName, String password);
}
