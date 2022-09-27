package ui.pantallas.newspaper.delete;

import domain.modelo.Newspaper;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.io.IOException;

public class NewsDeleteController extends BasePantallaController {

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

    private final NewsDeleteViewModel newsDeleteViewModel;

    @Inject
    public NewsDeleteController(NewsDeleteViewModel newsDeleteViewModel) {
        this.newsDeleteViewModel = newsDeleteViewModel;
    }


    @Override
    public void principalCargado() throws IOException {

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
    private void delete(ActionEvent actionEvent) {
        if (tableNews.getSelectionModel().getSelectedItem() == null) {
            getPrincipalController().sacarAlertError("error we");
        } else {
            newsDeleteViewModel.deleteNewspaper(tableNews.getSelectionModel().getSelectedItem().getNewspaperID());
        }
    }
}
