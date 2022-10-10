package ui.pantallas.principal;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.extern.log4j.Log4j2;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.io.IOException;

@Log4j2
public class PrincipalController {

    @FXML
    private final Alert alert;
    @FXML private MenuItem getSets;

    Instance<Object> instance;

    //cambio pantalla
    @FXML
    private BorderPane root;
    @FXML
    private MenuBar options;
    @FXML
    private MenuItem mostrarCartas;
    @FXML
    private MenuItem buscarCarta;
    @FXML
    private MenuItem filtrarCarta;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.ERROR);
    }

    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    public void initialize() {
        cargarPantalla(Pantallas.MENU);
    }

    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "mostrarCartas" -> cargarPantalla(Pantallas.CARTAS);
            case "buscarCarta" -> cargarPantalla(Pantallas.MAZOS);
            case "filtrarCarta" -> cargarPantalla(Pantallas.FILTRO);
            case "getSets" -> cargarPantalla(Pantallas.SETS);
        }
    }
}
