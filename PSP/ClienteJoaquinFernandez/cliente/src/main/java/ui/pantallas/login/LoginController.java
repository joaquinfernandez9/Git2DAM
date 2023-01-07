package ui.pantallas.login;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

public class LoginController extends BasePantallaController {


    @FXML
    private TextField usernameLog;
    @FXML
    private TextField passwordLog;
    @FXML
    private TextField usernameRegister;
    @FXML
    private TextField passwordRegister;
    @FXML
    private TextField email;
    @FXML
    private TextField idReader;

    private final LoginViewModel viewModel;

    @Inject
    public LoginController(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void login() {
        if (usernameLog.getText().isEmpty() || passwordLog.getText().isEmpty()) {
            getPrincipalController().errorAlert(UiConstants.RELLENA_LOS_CAMPOS);
        } else {
            viewModel.login(usernameLog.getText(), passwordLog.getText());

        }
    }

    @FXML
    private void register() {
        if (usernameLog.getText().isEmpty() || passwordLog.getText().isEmpty()
                || idReader.getText().isEmpty() || email.getText().isEmpty()) {
            getPrincipalController().errorAlert(UiConstants.RELLENA_LOS_CAMPOS);
        } else {
            viewModel.register(usernameLog.getText(), passwordLog.getText(),
                    email.getText(), Integer.parseInt(idReader.getText()));
        }
    }
}
