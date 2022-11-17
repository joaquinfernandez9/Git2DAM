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
                .stream().map(Newspaper::getName_newspaper).collect(Collectors.toList()));

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
        viewmodel.reloadState(-1, null);

    }

    @FXML
    private void search() {
        if (idNewspaper.getValue() == null) {
            getPrincipalController().errorAlert(UiConstants.NOT_FOUND);
        } else {
            viewmodel.reloadState(getNewspaperId(idNewspaper.getValue()), null);
        }
    }

    private int getNewspaperId(String name) {
        return viewmodel.getState().get().getNewspapersList()
                .stream().filter(n -> n.getName_newspaper().equals(name)).findFirst().get().getId();
    }

    @FXML
    private void searchOldest() {
        if (idNewspaper.getValue() == null) {
            getPrincipalController().errorAlert(UiConstants.NOT_FOUND);
        } else {
            viewmodel.getOldest(getNewspaperId(idNewspaper.getValue()));
        }
    }
}
