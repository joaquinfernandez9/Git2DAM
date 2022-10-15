package ui.pantallas.reader.addReader;

import domain.modelo.Reader;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    }

    @FXML
    private void addReader(ActionEvent actionEvent) {
    }
}
