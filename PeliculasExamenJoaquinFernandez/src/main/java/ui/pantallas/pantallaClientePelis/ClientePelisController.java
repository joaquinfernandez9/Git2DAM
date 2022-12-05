package ui.pantallas.pantallaClientePelis;

import domain.modelo.Actor;
import domain.modelo.Pelicula;
import domain.modelo.Usuario;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.util.List;

public class ClientePelisController extends BasePantallaController {


    private final ClientePelisViewModel viewModel;
    public TableView<Pelicula> tblPelisNoVistas;
    public TableColumn<String, Pelicula> nombrePeli;
    public TableColumn<List<Actor>, Pelicula> actoresPeli;
    public TableColumn<String, Pelicula> añoPeli;
    public TableView<Pelicula> tblPeliculasVistas;
    public TableColumn<String, Pelicula> nombrePelisVistas;
    public TableColumn<String, Pelicula> añoPelisVistas;
    public TableColumn<String, Pelicula> puntuacionPelisVistas;
    public TextField puntuacion;
    Usuario s;

    @Inject
    public ClientePelisController(ClientePelisViewModel clientePelisViewModel) {
        this.viewModel = clientePelisViewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();
        s = viewModel.getCliente(this.getPrincipalController().getCliente().getNombre());

        viewModel.getState().addListener((observableValue, clientePelisState, t1) ->{
            if (t1.getPeliculas()!= null){
                tblPelisNoVistas.getItems().clear();
                tblPelisNoVistas.getItems().addAll(t1.getPeliculas());
            }

            if (t1.getUsuario().getPeliculasUser()!=null){
                tblPeliculasVistas.getItems().clear();
                tblPeliculasVistas.getItems().addAll(t1.getUsuario().getPeliculasUser());
            }
        });

        viewModel.loadCliente(s);

    }

    public void initialize(){
        nombrePeli.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        añoPeli.setCellValueFactory(new PropertyValueFactory<>("año"));

        nombrePelisVistas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        añoPelisVistas.setCellValueFactory(new PropertyValueFactory<>("año"));
        puntuacionPelisVistas.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));
    }

    public void moverPelicula() {
        viewModel.moverPelicula(s, tblPelisNoVistas.getSelectionModel().getSelectedItem().getNombre(), "visto");
    }

    public void añadirPuntuacion() {
        viewModel.ponerPuntuacionPelicula(s, tblPeliculasVistas.getSelectionModel().getSelectedItem().getNombre(), Integer.parseInt(puntuacion.getText()));
    }
}
