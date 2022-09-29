package ui.pantallas.unicaCarta;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.common.BasePantallaController;

import java.io.IOException;

public class VerCartaController extends BasePantallaController {


    @FXML
    private TextField nombre;
    @FXML
    private Label mostrarCarta;


    private final VerCartaViewModel verCartaViewModel;

    @Inject
    VerCartaController(VerCartaViewModel verCartaViewModel) {
        this.verCartaViewModel = verCartaViewModel;
    }


    @FXML
    private void buscar(){
        if (nombre.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se ha proporcionado un nombre");
            alert.showAndWait();
        } else {
            mostrarCarta.setText(verCartaViewModel.verCartaNombre(nombre.getText()).toString());

        }
    }
}
