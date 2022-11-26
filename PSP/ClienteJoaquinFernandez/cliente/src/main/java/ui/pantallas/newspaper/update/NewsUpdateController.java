package ui.pantallas.newspaper.update;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

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

        idColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_NEWSPAPER));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.RELEASE_DATE));

        newsUpdateViewModel.getAll();

        newsUpdateViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getNewspaperList() != null) {
                tableNews.getItems().clear();
                tableNews.getItems().addAll(newValue.getNewspaperList());
            }
            if (newValue.getError() != null) {
                getPrincipalController().errorAlert(newValue.getError());
                newsUpdateViewModel.clearState();
            }
        });
    }

    @FXML
    private void update() {
        newsUpdateViewModel.updateNewspaper(
                tableNews.getSelectionModel().getSelectedItem().getId(),
                nameLabel.getText());
    }
}
