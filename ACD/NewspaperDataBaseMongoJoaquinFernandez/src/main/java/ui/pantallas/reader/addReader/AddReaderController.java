package ui.pantallas.reader.addReader;

import javafx.event.ActionEvent;
import model.Login;
import model.Newspaper;
import model.Reader;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;

public class AddReaderController extends BasePantallaController {
    @FXML
    private TableView<Newspaper> newspaperTable;
    @FXML
    private TableColumn<Newspaper, Integer> idTable;
    @FXML
    private TableColumn<Newspaper, String> nameTable;
    @FXML
    private TableColumn<Newspaper, LocalDate> releaseTableDate;
    @FXML
    private MFXTextField username;
    @FXML
    private MFXTextField password;
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private TableColumn<String, Reader> dateColumn;
    @FXML
    private MFXTextField idReader;
    @FXML
    private MFXTextField nameReader;
    @FXML
    private DatePicker dateReader;

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("cancellationDate"));

        idTable.setCellValueFactory(new PropertyValueFactory<>("_id"));
        nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        releaseTableDate.setCellValueFactory(new PropertyValueFactory<>("relDate"));

        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewModel.getAll());

        newspaperTable.getItems().clear();
        newspaperTable.getItems().setAll(viewModel.loadNewspaper());

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getReaderList() != null) {
                readersTable.getItems().clear();
                readersTable.getItems().addAll(newValue.getReaderList());
            }
        });

        viewModel.reloadState();

    }

    private final AddReaderViewModel viewModel;

    @Inject
    public AddReaderController(AddReaderViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void addReader() {
        Newspaper np = newspaperTable.getSelectionModel().getSelectedItem();
        if (nameReader.getText().isBlank() || dateReader.getValue() == null || username.getText().isBlank() || password.getText().isBlank() || np == null) {
            getPrincipalController().errorAlert("Error, fill all the gaps");
        } else {
            Reader reader = new Reader(nameReader.getText(), dateReader.getValue().toString());
            viewModel.addReader(reader, np);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setContentText("Reader added correctly");
            alert.showAndWait();
        }

    }

    public void getReaders() {
        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewModel.loadReaders(newspaperTable.getSelectionModel().getSelectedItem()));

    }
}
