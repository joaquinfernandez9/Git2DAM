package ui.pantallas.pantallaAdmin;

import domain.modelo.*;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.util.ArrayList;
import java.util.List;

public class AdminController extends BasePantallaController {


    @FXML
    private TableView<Usuario> tblClientes;
    @FXML
    private TableColumn<String, Usuario> nombreUsuario;

    @FXML
    private TextField nombreClienteAñadir;

    @FXML
    private TableView<Serie> tblSeries;
    @FXML
    private TableColumn<String, Serie> nombreSerie;
    @FXML
    private TableColumn<List<String>, Serie> capitulosList;
    @FXML
    private TextField nombreSerieAñadir;


    public TableView<Pelicula> tblPeliculas;
    public TableColumn<String, Pelicula> nombrePelicula;
    public TableColumn<String, Pelicula> añoPelicula;
    public TableColumn<String, Pelicula> actoresPelicula;


    public TableView<Actor> tblActores;
    public TableColumn<String, Actor> nombreActores;
    public TextField nombreActorAñadir;

    public TableView<Capitulo> tblCapitulos;
    public TableColumn<String, Capitulo> nombreCapitulo;
    public TextField episoriosSerie;




    private final AdminViewModel model;

    @Inject
    public AdminController(AdminViewModel model) {
        this.model = model;
    }

    @Override
    public void principalCargado(){
        super.principalCargado();

        model.getState().addListener(((observableValue, adminState, t1) -> {
            if (t1.getPeliculas()!=null){
                tblPeliculas.getItems().clear();
                tblPeliculas.getItems().addAll(t1.getPeliculas());
            }

            if (t1.getSeries() != null){
                tblSeries.getItems().clear();
                tblSeries.getItems().addAll(t1.getSeries());
            }
            if (t1.getUsuarios()!= null){
                tblClientes.getItems().clear();
                tblClientes.getItems().addAll(t1.getUsuarios());
            }


        }));
        model.load();

    }

    public void initialize(){
        nombreUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        nombreSerie.setCellValueFactory(new PropertyValueFactory<>("nombre"));


        nombrePelicula.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        añoPelicula.setCellValueFactory(new PropertyValueFactory<>("año"));

    }





    public void añadirActorSerie() {
        model.añadirActorCapitulo(nombreSerieAñadir.getText(),
                nombreCapitulo.getText(), nombreActorAñadir.getText());

    }

    public void añadirSerie() {
        model.añadirSerie(nombreSerieAñadir.getText(), new ArrayList<>());
    }

    public void darAltaCliente() {
     model.añadirUsuario(nombreClienteAñadir.getText());
    }
}
