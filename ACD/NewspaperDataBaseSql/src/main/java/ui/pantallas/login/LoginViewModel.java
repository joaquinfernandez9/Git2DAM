package ui.pantallas.login;

import model.Reader;
import services.LoginServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.ReaderServ;

public class LoginViewModel {

    private final ObjectProperty<LoginState> state;
    private final LoginServ loginServImpl;
    private final ReaderServ readerServ;


    @Inject
    public LoginViewModel(LoginServ loginServImpl, ReaderServ readerServ) {
        this.loginServImpl = loginServImpl;
        this.readerServ = readerServ;
        state = new SimpleObjectProperty<>(new LoginState(false, null, false, null));
    }

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public void login(String user, String password) {
        int response = loginServImpl.login(user, password);
        Reader r = readerServ.get(response).get();
        if (response == 0) {
            state.setValue(new LoginState(false, "User incorrect", false , null));
        } else if (response == -3) {
            state.setValue(new LoginState(false, "Database error", false, null));
        } else if (response < 0) {
            state.setValue(new LoginState(true, null, true, r));
        } else {
            state.setValue(new LoginState(true, null, false, r));
        }
    }



}



