package ui.pantallas.welcome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;

public class WelcomeController extends BasePantallaController {

    @FXML
    private Label labelWelcome;

    @Override
    public void principalCargado()  {
        super.principalCargado();

        if (!getPrincipalController().isAdmin){
            labelWelcome.setVisible(true);
            labelWelcome.setText("User " + getPrincipalController().r.getName_reader());
        } else {
            labelWelcome.setVisible(false);
        }
    }

}
