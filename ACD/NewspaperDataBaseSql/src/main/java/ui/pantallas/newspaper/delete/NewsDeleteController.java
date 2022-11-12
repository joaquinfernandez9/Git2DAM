package ui.pantallas.newspaper.delete;

import model.Newspaper;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.util.Date;
import java.util.Optional;

public class NewsDeleteController extends BasePantallaController {

    private final NewsDeleteViewModel newsDeleteViewModel;
    @FXML
    public TableView<Newspaper> tableNews;
    @FXML public TableColumn<Integer, Newspaper> idColumn;
    @FXML public TableColumn<String, Newspaper> nameColumn;
    @FXML public TableColumn<Date, Newspaper> dateColumn;

    @Inject
    public NewsDeleteController(NewsDeleteViewModel newsDeleteViewModel) {
        this.newsDeleteViewModel = newsDeleteViewModel;
    }


    @Override
    public void principalCargado() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));

        super.principalCargado();

        newsDeleteViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getNewspaperList() != null) {
                tableNews.getItems().clear();
                tableNews.getItems().addAll(newValue.getNewspaperList());
            }
        });

        newsDeleteViewModel.load();
    }

    @FXML
    private void delete() {
        if (tableNews.getSelectionModel().getSelectedItem() == null) {
            getPrincipalController().errorAlert("Error, nothing selected.");
        } else {
            if (newsDeleteViewModel.containsArticels(tableNews.getSelectionModel().getSelectedItem().getId())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete");
                alert.setHeaderText("This newspaper contains articles.");
                alert.setContentText("Are you ok with this?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    newsDeleteViewModel.deleteNewspaper(tableNews.getSelectionModel().getSelectedItem().getId());
                    getPrincipalController().infoAlert("Newspaper deleted.");
                    newsDeleteViewModel.load();
                }
            } else {
                newsDeleteViewModel.deleteNewspaper(tableNews.getSelectionModel().getSelectedItem().getId());
                getPrincipalController().infoAlert("Newspaper deleted.");
            }
        }
    }
}

