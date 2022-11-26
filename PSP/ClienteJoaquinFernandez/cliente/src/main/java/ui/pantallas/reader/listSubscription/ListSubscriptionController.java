package ui.pantallas.reader.listSubscription;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import model.Reader;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.util.Date;
import java.util.stream.Collectors;

public class ListSubscriptionController extends BasePantallaController {
    private final ListSubscriptionViewmodel viewmodel;
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private TableColumn<Date, Reader> dateColumn;
    @FXML
    private ComboBox<String> idNewspaper;

    @Inject
    public ListSubscriptionController(ListSubscriptionViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idNewspaper.getItems().addAll(viewmodel.getState().get().getNewspapersList()
                .stream().map(Newspaper::getName_newspaper).toList());

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
    private void search() {
        viewmodel.getAll();
    }

    @FXML
    public void searchOldest() {
        viewmodel.getOldest(idNewspaper.getSelectionModel().getSelectedIndex());
    }


}
