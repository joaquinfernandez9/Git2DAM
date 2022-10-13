package ui.pantallas.login;

import config.ConfigYaml;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import ui.pantallas.common.BasePantallaController;

import java.io.IOException;

public class LoginController extends BasePantallaController {

    private final LoginViewModel viewModel;
    private final ConfigYaml configYaml;


    @Inject
    public LoginController(LoginViewModel viewModel, ConfigYaml configYaml) {
        this.viewModel = viewModel;
        this.configYaml = configYaml;
    }
    @FXML
    private MFXTextField txtUserName;
    @FXML
    private MFXPasswordField txtPass;



    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();
    }

    @FXML
    private void login() {
        if (txtUserName.getText().isEmpty() || txtPass.getText().isEmpty()) {
            this.getPrincipalController().sacarAlertError("Error");
        } else if (viewModel.login(txtUserName.getText(), txtPass.getText())){
            this.getPrincipalController().onLoginAdmin();
        } else {
            this.getPrincipalController().sacarAlertError("Error");
        }
    }

}
