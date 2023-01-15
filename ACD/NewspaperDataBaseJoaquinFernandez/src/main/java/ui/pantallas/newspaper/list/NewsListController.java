package ui.pantallas.newspaper.list;

import javafx.event.ActionEvent;
import model.Article;
import model.Newspaper;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.util.Date;

public class NewsListController extends BasePantallaController {


    @FXML
    public TableView<Newspaper> tableNews;
    @FXML
    public TableColumn<Integer, Newspaper> idColumn;
    @FXML
    public TableColumn<String, Newspaper> nameColumn;
    @FXML
    public TableColumn<Date, Newspaper> dateColumn;

    private final NewsListViewModel newsListViewModel;
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<String, Article> idArticleColumn;
    @FXML
    private TableColumn<String, Article> titleColumn;
    @FXML
    private TableColumn<String, Article> description;

    @Inject
    public NewsListController(NewsListViewModel newsListViewModel) {
        this.newsListViewModel = newsListViewModel;
    }

    @Override
    public void principalCargado() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));

        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        description.setCellValueFactory(new PropertyValueFactory<>("type"));

        super.principalCargado();

        newsListViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getNewspaperList() != null) {
                tableNews.getItems().clear();
                tableNews.getItems().addAll(newValue.getNewspaperList());
            }
            if (newValue.getArticleList() != null) {
                articleTable.getItems().clear();
                articleTable.getItems().addAll(newValue.getArticleList());
            }

        });
        newsListViewModel.load();
    }

    @FXML
    private void getArticles() {
        Newspaper newspaper = tableNews.getSelectionModel().getSelectedItem();
        if (newspaper != null) {
            newsListViewModel.getArticles(newspaper.getId());

        }
    }
}
