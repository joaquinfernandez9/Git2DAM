package ui.pantallas.reader.addReader;

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
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewModel.getAll());

//        viewModel.getState().addListener((observable, oldValue, newValue)->{
//            if (newValue.getReaderList()!=null){
//                readersTable.getItems().clear();
//                readersTable.getItems().addAll(newValue.getReaderList());
//            }
//        });
//
//        viewModel.reloadState();

    }

    private final AddReaderViewModel viewModel;

    @Inject
    public AddReaderController(AddReaderViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void addReader() {
        if (nameReader.getText().isBlank() || dateReader.getValue() == null){
            getPrincipalController().sacarAlertError("Error, fill all the gaps");
        } else {
            Reader reader = new Reader(nameReader.getText(), dateReader.getValue());
            viewModel.addReader(reader);
            readersTable.getItems().add(reader);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setContentText("Reader added correctly");
            alert.showAndWait();

//            viewModel.reloadState();
        }

    }
}
