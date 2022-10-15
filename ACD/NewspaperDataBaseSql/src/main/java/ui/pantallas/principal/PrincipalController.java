package ui.pantallas.principal;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;

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
    public MenuItem rListSubscription;
    @FXML
    public MenuItem rAppendReadArticle;
    @FXML
    public MenuItem rListType;
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
    @FXML
    public MenuItem rUpdate;
    @FXML
    public MenuItem rAdd;


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
            case "rDelete":
                cargarPantalla(Pantallas.READER_DELETE);
                break;
            case "rAdd":
                cargarPantalla(Pantallas.READER_ADD);
                break;
            case "rUpdate":
                cargarPantalla(Pantallas.READER_UPDATE);
                break;
            case "rListSubscription":
                cargarPantalla(Pantallas.READER_LIST_SUBSCRIPTION);
                break;
            case "rListType":
                cargarPantalla(Pantallas.READER_LIST_TYPE);
                break;
            case "rAppendReadArticle":
                cargarPantalla(Pantallas.READER_ADD_READARTICLE);
                break;
        }
    }
}
