package ui.pantallas.reader.delete;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reader;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.time.LocalDate;
import java.util.Optional;

public class ReaderDeleteController extends BasePantallaController {
    private final ReaderDeleteViewmodel viewmodel;
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private TableColumn<LocalDate, Reader> dateColumn;

    @Inject
    public ReaderDeleteController(ReaderDeleteViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        nameColum.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_READER));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.BIRTH_READER));

        viewmodel.getAll();

        viewmodel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getReaderList() != null) {
                readersTable.getItems().clear();
                readersTable.getItems().addAll(newValue.getReaderList());
            }
            if (newValue.getError() != null) {
                getPrincipalController().errorAlert(newValue.getError());
                viewmodel.clearState();
            }
        });

    }

    @FXML
    private void deleteReader() {
        if (readersTable.getSelectionModel()
                .getSelectedItem() != null) {
            Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
            alertDelete.getButtonTypes().remove(ButtonType.OK);
            alertDelete.getButtonTypes().add(ButtonType.CANCEL);
            alertDelete.getButtonTypes().add(ButtonType.YES);
            alertDelete.setTitle(UiConstants.DELETE);
            alertDelete.setContentText(UiConstants.THIS_READER_MAY_HAVE_ARTICLES_AND_SUBSCRIPTIONS_DELETE_ANYWAY);
            Optional<ButtonType> res = alertDelete.showAndWait();


            res.ifPresent(buttonType -> {
                if (buttonType == ButtonType.YES) {
                    viewmodel.deleteReader(readersTable.getSelectionModel()
                            .getSelectedItem().getId());
                    readersTable.getItems().remove(readersTable.getSelectionModel()
                            .getSelectedItem());
                }
            });
            viewmodel.getAll();
        } else {
            getPrincipalController().errorAlert(UiConstants.NOT_FOUND);
        }

    }
}
