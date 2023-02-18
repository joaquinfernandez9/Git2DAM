package ui.pantallas.reader.delete;

import jakarta.enterprise.inject.New;
import model.Newspaper;
import model.Reader;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.time.LocalDate;
import java.util.Optional;

public class ReaderDeleteController extends BasePantallaController {
    private final ReaderDeleteViewmodel viewModel;
    @FXML
    private TableView<Reader> readersTable;
    @FXML
    private TableColumn<Integer, Reader> idColumn;
    @FXML
    private TableColumn<String, Reader> nameColum;
    @FXML
    private TableColumn<LocalDate, Reader> dateColumn;

    @FXML
    private TableView<Newspaper> newspaperTable;
    @FXML
    private TableColumn<Newspaper, Integer> idTable;
    @FXML
    private TableColumn<Newspaper, String> nameTable;
    @FXML
    private TableColumn<Newspaper, LocalDate> releaseTableDate;

    @Inject
    public ReaderDeleteController(ReaderDeleteViewmodel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("cancellationDate"));

        idTable.setCellValueFactory(new PropertyValueFactory<>("_id"));
        nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        releaseTableDate.setCellValueFactory(new PropertyValueFactory<>("relDate"));

        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewModel.getAll());
        newspaperTable.getItems().clear();
        newspaperTable.getItems().setAll(viewModel.loadNewspaper());
    }

    @FXML
    private void deleteReader() {
        Newspaper np = newspaperTable.getSelectionModel().getSelectedItem();
        if (readersTable.getSelectionModel()
                .getSelectedItem() != null) {
//            if (viewModel.hasSubscriptions(readersTable.getSelectionModel()
//                    .getSelectedItem().getId())) {
//                Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
//                alertDelete.getButtonTypes().remove(ButtonType.OK);
//                alertDelete.getButtonTypes().add(ButtonType.CANCEL);
//                alertDelete.getButtonTypes().add(ButtonType.YES);
//                alertDelete.setTitle("Delete");
//                alertDelete.setContentText("This reader has articles and subscriptions, delete anyway?");
//                Optional<ButtonType> res = alertDelete.showAndWait();
//
//                res.ifPresent(buttonType -> {
//                    if (buttonType == ButtonType.YES) {
                        viewModel.deleteReader(np, readersTable.getSelectionModel()
                                .getSelectedItem().getId());
                        readersTable.getItems().remove(readersTable.getSelectionModel()
                                .getSelectedItem());
//                    }
//                });

//            } else {
//                viewModel.deleteReader(np, readersTable.getSelectionModel()
//                        .getSelectedItem().getId());
//            }

        } else {
            getPrincipalController().errorAlert(UiConstants.NOT_FOUND);
        }



    }

    public void getReaders() {
        readersTable.getItems().clear();
        readersTable.getItems().addAll(viewModel.loadReaders(newspaperTable.getSelectionModel().getSelectedItem()));

    }

}
