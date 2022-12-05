package ui.pantallas.pantallaLogin;

import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.pantallas.common.BasePantallaController;

public class LoginController extends BasePantallaController {


    @FXML
    private MFXTextField txtUserName;


    private final LoginViewModel loginViewModel;

    @Inject
    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public void initialize() {
        loginViewModel.getState().addListener((observable, oldState, newState) -> {
            if (newState.getMensaje() != null) {
                this.getPrincipalController().sacarAlertError(newState.getMensaje());
            }
            if (newState.isLoginCorrecto() && !txtUserName.getText().equals("admin")) {
                //cambiar pantalla
                this.getPrincipalController().onLoginHecho(loginViewModel.getUsuario(txtUserName.getText()));
            }
            if (txtUserName.getText().equals("admin")) {
                this.getPrincipalController().loginAdmin();
            }
        });


    }
    public void doLogin (){
        if (txtUserName.getText().isEmpty()){
            this.getPrincipalController().sacarAlertError("Debe ingresar usuario");
        } else {
            loginViewModel.login(txtUserName.getText());
        }
    }

}
