package ui.pantallas.login;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import ui.pantallas.common.BasePantallaController;

public class LoginController extends BasePantallaController {

    private final LoginViewModel viewModel;


    @Inject
    public LoginController(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @FXML
    private MFXTextField txtUserName;
    @FXML
    private MFXPasswordField txtPass;



    @Override
    public void principalCargado()  {
        super.principalCargado();

        viewModel.getState().addListener((observable, oldState, newState) -> {
            if (newState.getMensaje() != null) {
                this.getPrincipalController().errorAlert(newState.getMensaje());
            }
            if (newState.isLoginAdmin() && newState.isLoginCorrecto()) {
                //cambiar pantalla
                this.getPrincipalController().infoAlert("Logged as admin");
                this.getPrincipalController().onLoginAdmin();
            }
            if (!newState.isLoginAdmin() && newState.isLoginCorrecto()) {
                this.getPrincipalController().infoAlert("Logged as user");
                this.getPrincipalController().r = newState.getReader();
                this.getPrincipalController().onLoginUser();
            }
        });
    }

    @FXML
    private void login() {
        if (txtUserName.getText().isEmpty() || txtPass.getText().isEmpty()) {
            this.getPrincipalController().infoAlert("some fields are empty");
        } else {
            viewModel.login(txtUserName.getText(), txtPass.getText());
        }
    }

}
