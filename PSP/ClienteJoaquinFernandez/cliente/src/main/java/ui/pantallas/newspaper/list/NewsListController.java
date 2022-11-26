package ui.pantallas.newspaper.list;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.util.Date;

public class NewsListController extends BasePantallaController {


    @FXML public TableView<Newspaper> tableNews;
    @FXML public TableColumn<Integer, Newspaper> idColumn;
    @FXML public TableColumn<String, Newspaper> nameColumn;
    @FXML public TableColumn<Date, Newspaper> dateColumn;

    private final NewsListViewModel newsListViewModel;

    @Inject
    public NewsListController(NewsListViewModel newsListViewModel) {
        this.newsListViewModel = newsListViewModel;
    }

    @Override
    public void principalCargado() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_NEWSPAPER));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>(UiConstants.RELEASE_DATE));

        super.principalCargado();

        newsListViewModel.getAll();

        newsListViewModel.getState().addListener((observable, oldValue, newValue) ->{
            if (newValue.getNewspaperList()!=null){
                tableNews.getItems().clear();
                tableNews.getItems().addAll(newValue.getNewspaperList());
            }
            if (newValue.getError()!=null){
                getPrincipalController().errorAlert(newValue.getError());
                newsListViewModel.clearState();
            }
        });
    }

}
