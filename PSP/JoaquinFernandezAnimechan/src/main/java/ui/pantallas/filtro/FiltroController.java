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

import java.io.IOException;

public class FiltroController extends BasePantallaController {
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

        raceCombo.getItems().addAll("continuous", "zombie", "fiend", "normal", "quick-play", "rock", "warrior",
                "winged beast", "spellcaster", "beast", "fairy", "equip", "field", "fish", "beast-warrior",
                "thunder", "machine", "sea serpent", "aqua", "plant", "dragon", "reptile", "counter", "psychic",
                "insect", "pyro", "dinosaur", "wyrm", "cyberse", "ritual", "divine-beast", "creator-god", "cyverse",
                "mai", "pegasus", "ishizu", "joey", "kaiba", "yugi");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lvlCol.setCellValueFactory(new PropertyValueFactory<>("level"));
        atkCol.setCellValueFactory(new PropertyValueFactory<>("atk"));
        defCol.setCellValueFactory(new PropertyValueFactory<>("def"));

        filtroViewModel.getState().addListener((observable, oldValue, newValue) -> {
            tablaCartas.getItems().clear();
            tablaCartas.getItems().addAll(newValue.cardsList().getData());
        });

        filtroViewModel.load();
    }

    @FXML
    private void buscarRaza() throws IOException {
        if (nameField.getText().isEmpty() ||
                raceCombo.getSelectionModel().isEmpty() || filterField.getText().isEmpty()) {
            getPrincipalController().sacarAlertError("Patata");
        } else {
            filtroViewModel.getCardsAtkRace(nameField.getText(),
                    String.valueOf(Integer.parseInt(atkField.getText())),
                    raceCombo.getSelectionModel().getSelectedItem(),
                    filterField.getText());
        }
    }


}
