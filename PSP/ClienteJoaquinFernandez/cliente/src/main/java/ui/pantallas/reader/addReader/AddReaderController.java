package ui.pantallas.reader.addReader;

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

public class AddReaderController extends BasePantallaController {
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
    private MFXTextField nameReader;
    @FXML
    private DatePicker dateReader;

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        nameColum.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_READER));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.BIRTH_READER));

        viewModel.getAll();

        viewModel.getState().addListener((observable, oldValue, newValue)->{
            if (newValue.getReaderList()!=null){
                readersTable.getItems().clear();
                readersTable.getItems().addAll(newValue.getReaderList());
            }
            if (newValue.getError()!=null){
                getPrincipalController().errorAlert(newValue.getError());
                viewModel.clearState();
            }
        });


    }

    private final AddReaderViewModel viewModel;

    @Inject
    public AddReaderController(AddReaderViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void addReader() {
        if (nameReader.getText().isBlank() || dateReader.getValue() == null || username.getText().isBlank() || password.getText().isBlank()) {
            getPrincipalController().errorAlert(UiConstants.FILL_FIELD);
        } else {
            Login login = new Login(username.getText(), password.getText());
            Reader reader = new Reader(nameReader.getText(), dateReader.getValue());
            reader.setLogin(login);
            viewModel.addReader(reader);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(UiConstants.CORRECT);
            alert.setContentText(UiConstants.READER_ADDED_CORRECTLY);
            alert.showAndWait();
        }
        viewModel.getAll();
    }
}
