package ui.pantallas.menu;

import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class MenuController extends BasePantallaController {

    public void moverCartas() {
        getPrincipalController().cargarPantalla(Pantallas.CARTAS);
    }

    public void moverMazos() {
        getPrincipalController().cargarPantalla(Pantallas.MAZOS);
    }

    public void moverFiltro(){
        getPrincipalController().cargarPantalla(Pantallas.FILTRO);
    }
}
