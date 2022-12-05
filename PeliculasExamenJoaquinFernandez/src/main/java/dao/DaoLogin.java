package dao;

import domain.modelo.Usuario;

public interface DaoLogin {
    boolean loguear(String nombre);

    Usuario getUsuario(String nombre);
}
