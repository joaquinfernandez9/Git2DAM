package ui.pantallas.filtro;

import domain.modelo.cards.DataItem;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;
import ui.common.Constantes;

import java.io.IOException;

public class FiltroController extends BasePantallaController {

    //de estos me salen un monton como que no los uso pero si los pulso me llevan a sitios, es por el plugin del javafx
    @FXML
    private TableView<DataItem> tablaCartas;
    @FXML
    private TableColumn<Integer, DataItem> idCol;
    @FXML
    private TableColumn<String, DataItem> nombreCol;
    @FXML
    private TableColumn<Integer, DataItem> lvlCol;
    @FXML
    private TableColumn<Integer, DataItem> atkCol;
    @FXML
    private TableColumn<Integer, DataItem> defCol;
    @FXML
    private TextField nameField;
    @FXML
    private TextField atkField;
    @FXML
    private TextField filterField;
    @FXML
    private ComboBox<String> raceCombo;


    private final FiltroViewModel filtroViewModel;

    @Inject
    FiltroController(FiltroViewModel filtroViewModel) {
        this.filtroViewModel = filtroViewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        raceCombo.getItems().addAll(Constantes.comboRaceStrings);

        //no se que hacer con el codigo duplicado aqui
        idCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.NAME));
        lvlCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.LEVEL));
        atkCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.ATK));
        defCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.DEF));

        filtroViewModel.getState().addListener((observable, oldValue, newValue) -> {
            tablaCartas.getItems().clear();
            tablaCartas.getItems().addAll(newValue.cardsList().getData());
        });

        filtroViewModel.load();
    }

    @FXML
    private void buscarRaza(){
        if (nameField.getText().isEmpty() ||
                raceCombo.getSelectionModel().isEmpty() || filterField.getText().isEmpty()) {
            getPrincipalController().sacarAlertError(Constantes.HAY_CAMPOS_VACIOS);
        } else if (filtroViewModel.getCardsAtkRace(nameField.getText(),
                String.valueOf(Integer.parseInt(atkField.getText())),
                raceCombo.getSelectionModel().getSelectedItem(),
                filterField.getText()).isRight()){
            filtroViewModel.getCardsAtkRace(nameField.getText(),
                    String.valueOf(Integer.parseInt(atkField.getText())),
                    raceCombo.getSelectionModel().getSelectedItem(),
                    filterField.getText());
        } else {
            getPrincipalController().sacarAlertError(Constantes.NO_HAY_CARTAS_CON_ESOS_DATOS);
        }
    }


}
