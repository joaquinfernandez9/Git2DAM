package ui.pantallas.menu;

import javafx.fxml.FXML;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class MenuController extends BasePantallaController {

    @FXML
    private void moverCartas() {
        getPrincipalController().cargarPantalla(Pantallas.CARTAS);
    }

    @FXML
    private void moverMazos() {
        getPrincipalController().cargarPantalla(Pantallas.MAZOS);
    }

    @FXML
    private void moverFiltro(){
        getPrincipalController().cargarPantalla(Pantallas.FILTRO);
    }
}
