package ui.pantallas.login;

import services.LoginServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LoginViewModel {

    private final ObjectProperty<LoginState> state;
    private final LoginServ loginServImpl;


    @Inject
    public LoginViewModel(LoginServ loginServImpl) {
        state = new SimpleObjectProperty<>(new LoginState(false, null));
        this.loginServImpl = loginServImpl;
    }

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public boolean login(String usuario, String password) {
        return loginServImpl.login(usuario, password);
    }



}



