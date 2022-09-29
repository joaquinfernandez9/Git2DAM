package ui.pantallas.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class MenuController extends BasePantallaController {

    @FXML
    private void moverCartas(ActionEvent actionEvent) {
        getPrincipalController().cargarPantalla(Pantallas.CARTAS);
    }

    @FXML
    private void moverMazos(ActionEvent actionEvent) {
        getPrincipalController().cargarPantalla(Pantallas.MAZOS);
    }

    @FXML
    private void moverFiltro(ActionEvent actionEvent){
        getPrincipalController().cargarPantalla(Pantallas.FILTRO);
    }
}
