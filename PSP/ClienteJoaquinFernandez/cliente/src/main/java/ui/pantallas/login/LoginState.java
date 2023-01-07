package ui.pantallas.login;

import lombok.Data;
import model.Login;

@Data
public class LoginState {
    private String mensaje;
    private Login log;

    public LoginState(String mensaje, Login log) {
        this.mensaje = mensaje;
        this.log = log;
    }

}
