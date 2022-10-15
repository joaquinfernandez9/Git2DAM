package ui.pantallas.reader.addReader;

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

public class AddReaderController extends BasePantallaController {
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private TableColumn<LocalDate, Reader> dateColumn;
    @FXML
    private MFXTextField idReader;
    @FXML
    private MFXTextField nameReader;
    @FXML
    private DatePicker dateReader;

    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        viewModel.getState().addListener((observable, oldValue, newValue)->{
            if (newValue.getReaderList()!=null){
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
        if (idReader.getText().isBlank() || nameReader.getText().isBlank() || dateReader.getValue() == null){
            getPrincipalController().sacarAlertError("Error, fill all the gaps");
        } else {
            viewModel.addReader(Integer.parseInt(idReader.getText()), nameReader.getText(), dateReader.getValue());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setContentText("Reader added correctly");
            alert.showAndWait();

            viewModel.reloadState();
        }

    }
}
