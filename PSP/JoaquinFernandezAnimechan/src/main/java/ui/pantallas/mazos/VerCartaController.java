package ui.pantallas.mazos;

import domain.modelo.cards.DataItem;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.common.BasePantallaController;

import java.io.IOException;

public class VerCartaController extends BasePantallaController {


    @FXML
    public TextField nombre;
    @FXML
    public Label mostrarCarta;


    VerCartaViewModel verCartaViewModel;

    @Inject
    VerCartaController(VerCartaViewModel verCartaViewModel) {
        this.verCartaViewModel = verCartaViewModel;
    }

    public void initialize() {

    }

    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();

    }


    public void buscar(ActionEvent actionEvent) throws IOException {
        if (nombre.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al eliminar bebida");
            alert.setContentText("No se puede eliminar una bebida sin seleccionarla");
            alert.showAndWait();
        } else {
            mostrarCarta.setText(verCartaViewModel.verCartaNombre(nombre.getText()).toString());

        }
    }
}
