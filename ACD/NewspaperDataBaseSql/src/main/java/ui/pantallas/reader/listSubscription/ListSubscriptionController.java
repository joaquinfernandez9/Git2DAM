package ui.pantallas.reader.listSubscription;

import javafx.scene.control.ComboBox;
import model.Newspaper;
import model.Reader;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class ListSubscriptionController extends BasePantallaController {
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private  TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private  TableColumn<LocalDate, Reader> dateColumn;
    @FXML
    private ComboBox<String> idNewspaper;

    private final ListSubscriptionViewmodel viewmodel;

    @Inject
    public ListSubscriptionController(ListSubscriptionViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idNewspaper.getItems().addAll(viewmodel.getState().get().getNewspapersList()
                .stream().map(Newspaper::getNewspaperName).collect(Collectors.toList()));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewmodel.getAll());

        viewmodel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getReaderList() != null) {
                readersTable.getItems().clear();
                readersTable.getItems().addAll(newValue.getReaderList());
            }
        });
        viewmodel.reloadState(-1,-1, null);

    }

    @FXML
    private  void search(ActionEvent actionEvent) {
        if (idNewspaper.getValue()==null){
            getPrincipalController().sacarAlertError(UiConstants.NOT_FOUND);
        }else {
            viewmodel.reloadState(getNewspaperId(idNewspaper.getValue()), -1, null);
        }
    }

    private int getNewspaperId(String name) {
        return viewmodel.getState().get().getNewspapersList()
                .stream().filter(n -> n.getNewspaperName().equals(name)).findFirst().get().getNewspaperID();
    }

    @FXML
    private void searchOldest() {
        if (idNewspaper.getValue()==null){
            getPrincipalController().sacarAlertError(UiConstants.NOT_FOUND);
        }else {
            viewmodel.reloadState(getNewspaperId(idNewspaper.getValue()), -1, null);
        }
    }
}
