package ui.pantallas.reader.updateReader;

import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reader;
import ui.pantallas.common.BasePantallaController;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateController extends BasePantallaController {
    private final UpdateViewModel viewModel;
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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewModel.getAll());

//        viewModel.getState().addListener((observable, oldValue, newValue) -> {
//            if (newValue.getReaderList() != null) {
//                readersTable.getItems().clear();
//                readersTable.getItems().addAll(newValue.getReaderList());
//            }
//        });
//        viewModel.reloadState();
    }

    @FXML
    private void updateReader() {
        if (readersTable.getSelectionModel().getSelectedItem() == null || nameReader.getText().isBlank()) {
            getPrincipalController().sacarAlertError("Error, fill all the gaps");
        } else {
            Reader reader = new Reader(
                    readersTable.getSelectionModel().getSelectedItem().getId(),
                    nameReader.getText(),
                    dateReader.getValue());
            viewModel.updateReader(reader);
            readersTable.getItems().remove(readersTable.getSelectionModel().getFocusedIndex());
            readersTable.getItems().add(readersTable.getSelectionModel().getFocusedIndex()+1, reader);
//            viewModel.reloadState();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setContentText("Reader updated correctly");
            alert.showAndWait();

        }

    }
}
