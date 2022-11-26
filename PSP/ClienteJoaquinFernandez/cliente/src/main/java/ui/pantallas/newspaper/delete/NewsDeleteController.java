package ui.pantallas.newspaper.delete;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.util.Date;
import java.util.Optional;

public class NewsDeleteController extends BasePantallaController {

    private final NewsDeleteViewModel newsDeleteViewModel;
    @FXML
    public TableView<Newspaper> tableNews;
    @FXML
    public TableColumn<Integer, Newspaper> idColumn;
    @FXML
    public TableColumn<String, Newspaper> nameColumn;
    @FXML
    public TableColumn<Date, Newspaper> dateColumn;

    @Inject
    public NewsDeleteController(NewsDeleteViewModel newsDeleteViewModel) {
        this.newsDeleteViewModel = newsDeleteViewModel;
    }


    @Override
    public void principalCargado() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_NEWSPAPER));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.RELEASE_DATE));

        super.principalCargado();

        newsDeleteViewModel.getAll();

        newsDeleteViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getNewspaperList() != null) {
                Platform.runLater(() -> {
                    tableNews.getItems().clear();
                    tableNews.getItems().addAll(newValue.getNewspaperList());
                });
            }
            if (newValue.getError() != null && !newValue.getError().contains("articles")) {
                Platform.runLater(() -> {
                    getPrincipalController().errorAlert(newValue.getError());
                    newsDeleteViewModel.clearState();
                });
            }

            if (newValue.getError() != null && newValue.getError().contains("articles")) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete");
                    alert.setHeaderText(newValue.getError());
                    alert.setContentText("Are you ok with this?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        newsDeleteViewModel.deleteConfirmed(tableNews.getSelectionModel().getSelectedItem().getId());
                    } else {
                        alert.close();
                    }
                });
            }
        });

    }

    @FXML
    private void delete() {
        if (tableNews.getSelectionModel().getSelectedItem() == null) {
            getPrincipalController().errorAlert(UiConstants.ERROR_NOTHING_SELECTED);
        } else {
            newsDeleteViewModel.deleteNewspaper(tableNews.getSelectionModel().getSelectedItem().getId());
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle(UiConstants.DELETE);
//            alert.setHeaderText(UiConstants.THIS_NEWSPAPER_CONTAINS_ARTICLES);
//            alert.setContentText(UiConstants.ARE_YOU_OK_WITH_THIS);
//
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.OK) {
//                getPrincipalController().infoAlert(UiConstants.NEWSPAPER_DELETED);
//                newsDeleteViewModel.getAll();
//            }
            newsDeleteViewModel.getAll();
        }
    }
}

