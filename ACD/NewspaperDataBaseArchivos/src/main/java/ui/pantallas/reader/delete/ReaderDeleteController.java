package ui.pantallas.reader.delete;

import domain.modelo.Reader;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.io.IOException;
import java.time.LocalDate;

public class ReaderDeleteController extends BasePantallaController {
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private TableColumn<LocalDate, Reader> dateColumn;

    private final ReaderDeleteViewmodel viewmodel;

    @Inject
    public ReaderDeleteController(ReaderDeleteViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        viewmodel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getReaderList() != null) {
                readersTable.getItems().clear();
                readersTable.getItems().addAll(newValue.getReaderList());
            }
        });
        viewmodel.reloadState();

    }

    @FXML
    private void deleteReader() {
        if (readersTable.getSelectionModel()
                .getSelectedItem()!=null){
            viewmodel.deleteReader(readersTable.getSelectionModel()
                    .getSelectedItem().getId());
            viewmodel.reloadState();
        } else {
            getPrincipalController().sacarAlertError(UiConstants.NOT_FOUND);
        }

    }
}
