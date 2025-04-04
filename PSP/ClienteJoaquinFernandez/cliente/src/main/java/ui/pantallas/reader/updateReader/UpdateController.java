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
import model.Reader;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.time.LocalDate;

public class UpdateController extends BasePantallaController {

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

    @Inject
    public UpdateController(UpdateViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        nameColum.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_READER));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.BIRTH_READER));

        viewModel.getAll();


        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getReaderList() != null) {
                readersTable.getItems().clear();
                readersTable.getItems().addAll(newValue.getReaderList());
            }
            if (newValue.getError() != null) {
                getPrincipalController().errorAlert(newValue.getError());
                viewModel.clearState();
            }
        });
    }

    @FXML
    private void updateReader() {
        if (readersTable.getSelectionModel().getSelectedItem() == null) {
            getPrincipalController().errorAlert(UiConstants.ERROR_SELECT_A_READER);
        } else {
            Login login = new Login();
            if (username.getText().isEmpty()) {
                login.setUser(null);
            } else {
                login.setUser(username.getText());
            }
            if (password.getText().isEmpty()) {
                login.setPassword(null);
            } else {
                login.setPassword(password.getText());
            }

            Reader reader = new Reader(
                    readersTable.getSelectionModel().getSelectedItem().getId(),
                    nameReader.getText(),
                    dateReader.getValue(),
                    login);
            viewModel.updateReader(reader);
            readersTable.getItems().remove(readersTable.getSelectionModel().getFocusedIndex());
            readersTable.getItems().add(readersTable.getSelectionModel().getFocusedIndex() + 1, reader);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle(UiConstants.CORRECT);
            alert.setContentText(UiConstants.READER_UPDATED_CORRECTLY);
            alert.showAndWait();
            viewModel.getAll();
        }

    }
}
