package ui.pantallas.reader.listType;

import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reader;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.time.LocalDate;

public class ListTypeController extends BasePantallaController {
    private final ListTypeViewmodel viewmodel;
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private TableColumn<LocalDate, Reader> dateColumn;
    @FXML
    private MFXTextField description;

    @Inject
    public ListTypeController(ListTypeViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name_reader"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("birth_reader"));

        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewmodel.getAll());

        viewmodel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getReaderList() != null) {
                readersTable.getItems().clear();
                readersTable.getItems().addAll(newValue.getReaderList());
            }
        });

        viewmodel.reloadState();

    }

    @FXML
    private void search() {
        if (description.getText().isEmpty()){
            getPrincipalController().errorAlert(UiConstants.NOT_FOUND);
        } else {
            viewmodel.getByDesc(description.getText());
        }
    }
}

