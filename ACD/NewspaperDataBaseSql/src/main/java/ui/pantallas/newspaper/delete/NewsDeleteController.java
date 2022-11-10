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

import java.io.IOException;
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
    public TableColumn<Double, Newspaper> priceColumn;
    @FXML
    public TableColumn<String, Newspaper> directorColumn;

    @Inject
    public NewsDeleteController(NewsDeleteViewModel newsDeleteViewModel) {
        this.newsDeleteViewModel = newsDeleteViewModel;
    }


    @Override
    public void principalCargado() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("newspaperID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("newspaperName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));

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
            getPrincipalController().sacarAlertError("Error, nothing selected.");
        } else {
            Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
            alertDelete.getButtonTypes().remove(ButtonType.OK);
            alertDelete.getButtonTypes().add(ButtonType.CANCEL);
            alertDelete.getButtonTypes().add(ButtonType.YES);
            alertDelete.setTitle("Delete");
            alertDelete.setContentText("This newspaper may contains articles, delete anyway?");
            Optional<ButtonType> res = alertDelete.showAndWait();


            res.ifPresent(buttonType -> {
                if (buttonType == ButtonType.YES) {
                    newsDeleteViewModel.deleteNewspaper(tableNews.getSelectionModel().getSelectedItem().getNewspaperID());
                }
            });
        }
    }
}

