package ui.pantallas.pantallaLogin;

import domain.modelo.Usuario;
import domain.servicios.ServiciosLogin;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LoginViewModel {

    private final ServiciosLogin serviciosLogin;

    private final ObjectProperty<LoginState> state;
    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }


    @Inject LoginViewModel(ServiciosLogin serviciosLogin){
        this.serviciosLogin = serviciosLogin;
        state = new SimpleObjectProperty<>(new LoginState(false,null));
    }

    public void login(String nombre){
        if (serviciosLogin.login(nombre) && !nombre.equals("admin")){
            state.setValue(new LoginState(true, null));
        } else if (nombre.equals("admin")){
            state.setValue(new LoginState(true, null));
        } else {
            state.setValue(new LoginState(false, "Error, no existe o introducido mal."));
        }
    }

    public Usuario getUsuario(String usuario){
        return serviciosLogin.getUsuario(usuario);
    }
}
