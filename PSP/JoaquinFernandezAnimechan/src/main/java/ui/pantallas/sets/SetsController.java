package ui.pantallas.sets;

import domain.modelo.ListaSetsCarta;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;

public class SetsController extends BasePantallaController {

    @FXML
    private TableView<ListaSetsCarta> tabla;
    @FXML
    private TableColumn<String, ListaSetsCarta> codigoColunm;
    @FXML
    private TableColumn<String, ListaSetsCarta> nombreColunm;

    private final SetsViewModel viewModel;

    @Inject
    public SetsController(SetsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void principalCargado(){
        super.principalCargado();

        codigoColunm.setCellValueFactory(new PropertyValueFactory<>("codigoSet"));
        nombreColunm.setCellValueFactory(new PropertyValueFactory<>("nombreSet"));

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            tabla.getItems().clear();
            tabla.getItems().addAll(newValue.listaSetsCarta());
        });

        viewModel.reloadState();


    }


}
