package ui.pantallas.welcome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

public class WelcomeController extends BasePantallaController {

    @FXML
    private Label labelWelcome;

    @Override
    public void principalCargado()  {
        super.principalCargado();

        if (!getPrincipalController().isAdmin){
            labelWelcome.setVisible(true);
            labelWelcome.setText(UiConstants.USER + getPrincipalController().r.getName_reader());
        } else {
            labelWelcome.setVisible(false);
        }
    }

}
