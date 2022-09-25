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


    public void initialize() {


        viewModel.getState().addListener((observable, oldState, newState) -> {
            if (txtPass.getText().equals("root") && txtUserName.getText().equals("root")){
                this.getPrincipalController().onLoginAdmin();
            } else {
                this.getPrincipalController().sacarAlertError("Error");
            }
        });
    }

    @FXML
    private void login() {
        if (txtUserName.getText().isEmpty() || txtPass.getText().isEmpty()) {
            this.getPrincipalController().sacarAlertError("Error");
        } else {
            viewModel.login(txtUserName.getText(), txtPass.getText());
        }
    }

}
