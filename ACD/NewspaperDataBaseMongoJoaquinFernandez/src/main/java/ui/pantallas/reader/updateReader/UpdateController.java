package ui.pantallas.reader.updateReader;

import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Login;
import model.Newspaper;
import model.Reader;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.time.LocalDate;

public class UpdateController extends BasePantallaController {
    public static final String READER_UPDATED_CORRECTLY = "Reader updated correctly";
    private final UpdateViewModel viewModel;
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
    private TableColumn<LocalDate, Reader> dateColumn;
    @FXML
    private DatePicker dateReader;
    @FXML
    private MFXTextField nameReader;

    @FXML
    private TableView<Newspaper> newspaperTable;
    @FXML
    private TableColumn<Newspaper, Integer> idTable;
    @FXML
    private TableColumn<Newspaper, String> nameTable;
    @FXML
    private TableColumn<Newspaper, LocalDate> releaseTableDate;

    @Inject
    public UpdateController(UpdateViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        nameColum.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_READER));
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

    @FXML
    private void updateReader() {
        if (readersTable.getSelectionModel().getSelectedItem() == null) {
            getPrincipalController().errorAlert(UiConstants.ERROR_SELECT_A_READER);
        } else {
            Reader reader = new Reader(
                    readersTable.getSelectionModel().getSelectedItem().getId(),
                    nameReader.getText(),
                    dateReader.getValue().toString());
            viewModel.updateReader(reader);
            readersTable.getItems().remove(readersTable.getSelectionModel().getFocusedIndex());
            readersTable.getItems().add(readersTable.getSelectionModel().getFocusedIndex() + 1, reader);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle(UiConstants.CORRECT);
            alert.setContentText(READER_UPDATED_CORRECTLY);
            alert.showAndWait();

        }

    }

    public void getReaders() {
        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewModel.loadReaders(newspaperTable.getSelectionModel().getSelectedItem()));

    }
}
