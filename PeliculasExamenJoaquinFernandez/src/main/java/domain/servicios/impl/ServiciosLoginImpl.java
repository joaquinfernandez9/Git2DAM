package domain.servicios.impl;

import dao.DaoLogin;
import domain.modelo.Usuario;
import domain.servicios.ServiciosLogin;
import jakarta.inject.Inject;

public class ServiciosLoginImpl implements ServiciosLogin {
    private final DaoLogin daoLogin;

    @Inject
    public ServiciosLoginImpl(DaoLogin daoLogin){
        this.daoLogin = daoLogin;
    }

    @Override
    public boolean login(String usuario){
        return daoLogin.loguear(usuario);
    }

    @Override
    public Usuario getUsuario(String usuario){
        return daoLogin.getUsuario(usuario);
    }
}
