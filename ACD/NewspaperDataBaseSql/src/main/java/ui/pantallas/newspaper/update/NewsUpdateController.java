package ui.pantallas.newspaper.update;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;

public class NewsUpdateController extends BasePantallaController {
    @FXML
    private TextField nameLabel;
    @FXML
    private TableView<Newspaper> tableNews;
    @FXML
    private TableColumn<Integer, Newspaper> idColumn;
    @FXML
    private TableColumn<String, Newspaper> nameColumn;
    @FXML
    private TableColumn<LocalDate, Newspaper> dateColumn;

    private final NewsUpdateViewModel newsUpdateViewModel;

    @Inject
    public NewsUpdateController(NewsUpdateViewModel newsUpdateViewModel) {
        this.newsUpdateViewModel = newsUpdateViewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));

        newsUpdateViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getNewspaperList() != null) {
                tableNews.getItems().clear();
                tableNews.getItems().addAll(newValue.getNewspaperList());
            }
        });
        newsUpdateViewModel.load();
    }

    @FXML
    private void update() {
        newsUpdateViewModel.updateNewspaper(
                tableNews.getSelectionModel().getSelectedItem().getId(),
                nameLabel.getText());
    }
}
