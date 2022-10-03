package ui.pantallas.unicaCarta;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.common.BasePantallaController;
import ui.common.Constantes;

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
            alert.setTitle(Constantes.ERROR);
            alert.setHeaderText(Constantes.NO_SE_HA_PROPORCIONADO_UN_NOMBRE);
            alert.showAndWait();
        } else {
            if (verCartaViewModel.verCartaNombre(nombre.getText()).isRight()){
                mostrarCarta.setText(verCartaViewModel
                        .verCartaNombre(nombre.getText()).get().toString());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(Constantes.ERROR);
                alert.setHeaderText(Constantes.NO_HAY_CARTAS_CON_ESE_NOMBRE);
                alert.showAndWait();
            }
        }
    }
}
