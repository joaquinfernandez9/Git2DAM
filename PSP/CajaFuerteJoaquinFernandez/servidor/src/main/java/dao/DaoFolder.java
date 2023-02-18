package dao;

import domain.Vault;

import java.util.List;

public interface DaoFolder {

    List<Vault> getAll();

    Vault insert(Vault folder);

    int delete(int id);

    boolean checkPass(String password, int id);

    boolean checkUser(String user, int id);

}
