package dao.impl;

import dao.DaoLogin;
import dao.DataBase;
import domain.modelo.Usuario;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

public class DaoLoginImpl implements DaoLogin {

    private final DataBase dataBase;

    @Inject
    public DaoLoginImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean loguear(String nombre){
        List<Usuario> usuarios = dataBase.loadUsuarios();
        if (!Objects.equals(nombre, "admin")){
            Usuario user = usuarios.stream().filter(usuario -> usuario.getNombre().equals(nombre))
                    .findFirst().orElse(null);
            return usuarios.contains(user);
        } else return "admin".equals(nombre);
    }

    @Override
    public Usuario getUsuario(String nombre){
        List<Usuario> usuarios = dataBase.loadUsuarios();
        return usuarios.stream().filter(usuario -> usuario.getNombre().equals(nombre))
                .findFirst().orElse(null);
    }

}
