package ui.pantallas.principal;

//import dao.dataBase.DataBaseConnectionPool;
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
import model.Reader;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {

    @FXML
    private final Alert alert;
//    private final DataBaseConnectionPool db;
    public boolean isAdmin;
    public Reader r;
    @FXML
    private MenuItem querys;
    Instance<Object> instance;
    @FXML
    private MenuItem rSubscribe;
    @FXML
    private MenuItem logout;
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
        cargarPantalla(Pantallas.LOGIN);
        menuBar.setVisible(false);
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

    public void adminVisible(boolean admin) {
        npAdd.setVisible(admin);
        npDelete.setVisible(admin);
        npList.setVisible(admin);
        npUpdate.setVisible(admin);
        rListSubscription.setVisible(admin);
        rListType.setVisible(admin);
        rUpdate.setVisible(admin);
        rAdd.setVisible(admin);
        rDelete.setVisible(admin);
        artAdd.setVisible(admin);
        artList.setVisible(admin);
        artDelete.setVisible(admin);
        artUpdate.setVisible(admin);
        querys.setVisible(admin);
    }

    public void onLoginAdmin() {
        isAdmin = true;
        menuBar.setVisible(true);
        adminVisible(true);
        rAppendReadArticle.setVisible(false);
        rSubscribe.setVisible(false);
        cargarPantalla(Pantallas.WELCOME_SCREEN);
    }

    public void onLoginUser() {
        isAdmin = false;
        adminVisible(false);
        menuBar.setVisible(true);
        rAppendReadArticle.setVisible(true);
        rSubscribe.setVisible(true);
        cargarPantalla(Pantallas.WELCOME_SCREEN);
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    public void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Quit application");
        alert.setContentText("Close without saving?");
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();


        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
//                db.closePool();
            }
        });
    }

    public void menuClick(javafx.event.ActionEvent actionEvent) {
        switch (((javafx.scene.control.MenuItem) actionEvent.getSource())
                .getId()) {
            case "npList":
                cargarPantalla(Pantallas.NEWS_LIST);
                break;
            case "npAdd":
                cargarPantalla(Pantallas.NEWS_ADD);
                break;
            case "npUpdate":
                cargarPantalla(Pantallas.NEWS_UPDATE);
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
            case "artUpdate":
                cargarPantalla(Pantallas.ARTICLE_UPDATE);
                break;
            case "artDelete":
                cargarPantalla(Pantallas.ARTICLE_DELETE);
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
            case "rSubscribe":
                cargarPantalla(Pantallas.READER_SUBSCRIBE);
                break;
            case "logout":
                logout();
                break;
            case "querys":
                cargarPantalla(Pantallas.QUERYS);
                break;

        }
    }

    public void logout() {
        isAdmin = false;
        r = null;
        menuBar.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }
}
