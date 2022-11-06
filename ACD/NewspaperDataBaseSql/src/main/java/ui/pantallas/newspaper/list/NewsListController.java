package ui.pantallas.newspaper.list;

import model.Newspaper;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.io.IOException;

public class NewsListController extends BasePantallaController {


    @FXML public TableView<Newspaper> tableNews;
    @FXML public TableColumn<Integer, Newspaper> idColumn;
    @FXML public TableColumn<String, Newspaper> nameColumn;
    @FXML public TableColumn<Double, Newspaper> priceColumn;
    @FXML public TableColumn<String, Newspaper> directorColumn;

    private final NewsListViewModel newsListViewModel;

    @Inject
    public NewsListController(NewsListViewModel newsListViewModel) {
        this.newsListViewModel = newsListViewModel;
    }



    @Override
    public void principalCargado() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("newspaperID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("newspaperName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));

        super.principalCargado();

        newsListViewModel.getState().addListener((observable, oldValue, newValue) ->{
            if (newValue.getNewspaperList()!=null){
                tableNews.getItems().clear();
                tableNews.getItems().addAll(newValue.getNewspaperList());
            }
        });

        newsListViewModel.load();
    }




}
