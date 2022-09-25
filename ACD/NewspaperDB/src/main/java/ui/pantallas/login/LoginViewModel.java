package ui.pantallas.login;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LoginViewModel {

    private final ObjectProperty<LoginState> state;

    @Inject
    public LoginViewModel() {
        state = new SimpleObjectProperty<>(new LoginState(false, null));
    }

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public void login(String usuario, String password) {
        if (!usuario.equals("root") && !password.equals("root") ) {
            state.set(new LoginState(true, null));
        } else {
            state.set(new LoginState(false, "Usuario o contraseña incorrectos"));
        }
    }

}



