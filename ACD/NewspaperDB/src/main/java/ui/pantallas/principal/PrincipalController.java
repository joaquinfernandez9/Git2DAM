package ui.pantallas.principal;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {

    Instance<Object> instance;
    @FXML
    private BorderPane root;
    @FXML
    private MenuBar options;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }

    public void initialize() {
        cargarPantalla(Pantallas.LOGIN);
        options.setVisible(false);
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            //nullpointer 1 falta extends en controller 2 fxml sin fx:controller 3 pane pantalla= ruta mal Config
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }

    @FXML
    private final Alert alert;

    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void onLoginAdmin() {
        options.setVisible(true);
        cargarPantalla(Pantallas.WELCOME_SCREEN);
    }

    //change screen menu

    @FXML
    public MenuItem logout;
    @FXML
    public MenuItem npList;
    @FXML
    public MenuItem npAdd;
    @FXML
    public MenuItem npUpdate;
    @FXML
    public MenuItem npDelete;
    @FXML
    public MenuItem rList;
    @FXML
    public MenuItem rAdd;
    @FXML
    public MenuItem rUpdate;
    @FXML
    public MenuItem rDelete;
    @FXML
    public MenuItem artList;
    @FXML
    public MenuItem artAdd;
    @FXML
    public MenuItem artUpdate;
    @FXML
    public MenuItem artDelete;


    public void menuClick(javafx.event.ActionEvent actionEvent) {
        switch (((javafx.scene.control.MenuItem) actionEvent.getSource())
                .getId()){
            case "npList":
                cargarPantalla(Pantallas.NEWS_LIST);
                break;
            case "npDelete":
                cargarPantalla(Pantallas.NEWS_DELETE);
                break;
            case "artList":
                cargarPantalla(Pantallas.ARTICLE_LIST);
                break;
            case "artAdd":
                cargarPantalla(Pantallas.ARTICLE_ADD);
                break;
        }
    }
}
