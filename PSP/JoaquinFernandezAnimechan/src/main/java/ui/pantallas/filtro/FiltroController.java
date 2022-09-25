package ui.pantallas.filtro;

import domain.modelo.cards.DataItem;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;

import java.io.IOException;

public class FiltroController extends BasePantallaController {
    @FXML
    public TableView<DataItem> tablaCartas;
    @FXML
    public TableColumn<Integer, DataItem> idCol;
    @FXML
    public TableColumn<String, DataItem> nombreCol;
    @FXML
    public TableColumn<Integer, DataItem> lvlCol;
    @FXML
    public TableColumn<Integer, DataItem> atkCol;
    @FXML
    public TableColumn<Integer, DataItem> defCol;
    @FXML
    public TextField nameField;
    @FXML
    public TextField atkField;
    @FXML
    public TextField filterField;
    @FXML
    public ComboBox<String> raceCombo;


    FiltroViewModel filtroViewModel;

    @Inject
    FiltroController(FiltroViewModel filtroViewModel){
        this.filtroViewModel = filtroViewModel;
    }

    public void initialize() {
        raceCombo.getItems().addAll("continuous", "zombie", "fiend", "normal", "quick-play", "rock","warrior",
 "winged beast", "spellcaster", "beast", "fairy", "equip", "field", "fish", "beast-warrior",
 "thunder", "machine", "sea serpent", "aqua", "plant", "dragon", "reptile", "counter", "psychic",
 "insect", "pyro", "dinosaur", "wyrm", "cyberse", "ritual", "divine-beast", "creator-god", "cyverse",
 "mai", "pegasus", "ishizu", "joey", "kaiba", "yugi");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lvlCol.setCellValueFactory(new PropertyValueFactory<>("level"));
        atkCol.setCellValueFactory(new PropertyValueFactory<>("atk"));
        defCol.setCellValueFactory(new PropertyValueFactory<>("def"));

    }
    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();

        filtroViewModel.getState().addListener((observable, oldValue, newValue) -> {
            tablaCartas.getItems().clear();
            tablaCartas.getItems().addAll(newValue.getCardsList().getData());
        });

        filtroViewModel.load();
    }

    public void buscarRaza() throws IOException {
        filtroViewModel.getCardsAtkRace(nameField.getText(), Integer.parseInt(atkField.getText()), raceCombo.getSelectionModel().getSelectedItem(), filterField.getText());
    }


//    public void buscarRaza() throws IOException {
//
//    }
}
