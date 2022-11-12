package ui.pantallas.newspaper.login;

import lombok.Data;
import model.Login;
import model.Reader;

@Data
public class LoginState {

    private final boolean loginCorrecto;
    private final String mensaje;
    private final boolean loginAdmin;
    private final Reader reader;
}
