package ui.pantallas.principal;

import domain.modelo.Usuario;
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
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;

@Log4j2
public class PrincipalController {
    @FXML
    private BorderPane root;
    @FXML
    private MenuBar menuPrincipal;
    @FXML
    private MenuItem menuPrincipalAdmin;
    @FXML
    private MenuItem menuClientesPeliculas;
    @FXML
    private MenuItem menuClientesSeries;
    @FXML
    private MenuItem menuItemLogout;


    private Usuario usuarioActual;
    private boolean isAdmin;

    Instance<Object> instance;

    @FXML
    private final Alert alert;

    @Inject
    public PrincipalController(Instance<Object> instance){
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    public Usuario getCliente() {
        return usuarioActual;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarPantalla(Pantallas pantalla) {
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
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    public void menuClick(ActionEvent event) {
        switch (((javafx.scene.control.MenuItem) event.getSource()).getId()){
            case "menuPrincipalAdmin":
                cargarPantalla(Pantallas.ADMIN);
                break;
            case "menuClientesPeliculas":
                cargarPantalla(Pantallas.CLIENTE_PELIS);
                break;
            case "menuClientesSeries":
                cargarPantalla(Pantallas.CLIENTE_SERIES);
                break;
            case "menuItemLogout":
                logout();
                break;
        }
    }

    public void logout() {
        usuarioActual = null;
        isAdmin = false;
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    public void onLoginHecho(Usuario usuario){
        usuarioActual = usuario;
        isAdmin = false;
        menuPrincipal.setVisible(true);
        menuClientesSeries.setVisible(true);
        menuClientesPeliculas.setVisible(true);
        menuPrincipalAdmin.setVisible(false);

        cargarPantalla(Pantallas.CLIENTE_PELIS);
    }

    public void loginAdmin(){

        isAdmin = true;
        menuPrincipal.setVisible(true);
        menuClientesSeries.setVisible(true);
        menuClientesPeliculas.setVisible(true);
        menuPrincipalAdmin.setVisible(true);

        cargarPantalla(Pantallas.ADMIN);
    }
}
