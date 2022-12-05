package domain.servicios;

import domain.modelo.Usuario;

public interface ServiciosLogin {
    boolean login(String usuario);

    Usuario getUsuario(String usuario);
}
