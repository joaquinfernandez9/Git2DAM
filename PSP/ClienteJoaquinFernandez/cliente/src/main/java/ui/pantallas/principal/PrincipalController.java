package ui.pantallas.principal;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.application.Platform;
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
import model.Reader;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {


    @FXML
    private final Alert alert;
    @FXML
    private MenuItem querys;

    Instance<Object> instance;
    @FXML
    private MenuItem npList;
    @FXML
    private MenuItem npAdd;
    @FXML
    private MenuItem npUpdate;
    @FXML
    private MenuItem npDelete;
    @FXML
    private MenuItem rListSubscription;
    @FXML
    private MenuItem rAppendReadArticle;
    @FXML
    private MenuItem rListType;
    @FXML
    private MenuItem rDelete;
    @FXML
    private MenuItem artList;
    @FXML
    private MenuItem artAdd;
    @FXML
    private MenuItem artUpdate;
    @FXML
    private MenuItem artDelete;
    //change screen menu
    @FXML
    private MenuItem rUpdate;
    @FXML
    private MenuItem rAdd;
    @FXML
    private MenuItem logoutUser;
    @FXML
    private BorderPane root;
    @FXML
    private MenuBar menuBar;
    private Stage primaryStage;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }

    public void initialize() {
        cargarPantalla(Pantallas.WELCOME_SCREEN);
        menuBar.setVisible(true);
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

    public void errorAlert(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void infoAlert(String mensaje) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    public void setStage(Stage stage) {
        primaryStage = stage;
    }



    public void menuClick(javafx.event.ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource())
                .getId()) {
            case "npList" -> cargarPantalla(Pantallas.NEWS_LIST);
            case "npAdd" -> cargarPantalla(Pantallas.NEWS_ADD);
            case "npUpdate" -> cargarPantalla(Pantallas.NEWS_UPDATE);
            case "npDelete" -> cargarPantalla(Pantallas.NEWS_DELETE);
            case "rDelete" -> cargarPantalla(Pantallas.READER_DELETE);
            case "rAdd" -> cargarPantalla(Pantallas.READER_ADD);
            case "rUpdate" -> cargarPantalla(Pantallas.READER_UPDATE);
            case "rListSubscription" -> cargarPantalla(Pantallas.READER_LIST_SUBSCRIPTION);
            case "rListType" -> cargarPantalla(Pantallas.READER_LIST_TYPE);
            case "querys" -> cargarPantalla(Pantallas.QUERYS);
        }
    }
}
