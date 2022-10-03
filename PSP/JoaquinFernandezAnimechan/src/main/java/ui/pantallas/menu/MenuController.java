package ui.pantallas.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.common.BasePantallaController;
import ui.common.Constantes;
import ui.common.Pantallas;

public class MenuController extends BasePantallaController {


    @FXML private ImageView imageView;

    public void principalCargado(){
        super.principalCargado();

        try {
            imageView.setImage(new Image(getClass().getResourceAsStream(Constantes.IMG_DUEL_TIME_GIF)));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

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
