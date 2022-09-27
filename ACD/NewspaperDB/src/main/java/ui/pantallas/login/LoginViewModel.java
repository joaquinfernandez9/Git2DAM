package ui.pantallas.login;

import domain.services.LoginServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LoginViewModel {

    private final ObjectProperty<LoginState> state;
    private final LoginServ loginServ;


    @Inject
    public LoginViewModel(LoginServ loginServ) {
        state = new SimpleObjectProperty<>(new LoginState(false, null));
        this.loginServ = loginServ;
    }

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public boolean login(String usuario, String password) {
        return loginServ.login(usuario, password);
    }



}



