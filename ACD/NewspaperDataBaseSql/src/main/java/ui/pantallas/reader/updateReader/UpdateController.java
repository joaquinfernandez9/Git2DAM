package ui.pantallas.reader.updateReader;

import domain.modelo.Reader;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateController extends BasePantallaController {
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private TableColumn<LocalDate, Reader> dateColumn;
//    @FXML
//    private DatePicker dateReader;
    @FXML
    private MFXTextField nameReader;

    private final UpdateViewModel viewModel;

    @Inject
    public UpdateController(UpdateViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

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
        if (readersTable.getSelectionModel().getSelectedItem() == null || nameReader.getText().isBlank()){
            getPrincipalController().sacarAlertError("Error, fill all the gaps");
        }else {
            viewModel.updateReader(
                    readersTable.getSelectionModel().getSelectedItem().getId(),
                    nameReader.getText()/*,
                dateReader.getValue()*/);
            viewModel.reloadState();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setContentText("Reader updated correctly");
            alert.showAndWait();
        }

    }
}