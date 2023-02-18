package ui.pantallas.newspaper.list;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Article;
import model.Newspaper;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.util.Date;
import java.util.Map;

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


    @FXML
    public ListView<Map<String,Integer>> numberArticlesType;
    @Inject
    public NewsListController(NewsListViewModel newsListViewModel) {
        this.newsListViewModel = newsListViewModel;
    }

    @Override
    public void principalCargado() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("relDate"));

        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        titleColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));
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
            newsListViewModel.getArticles(newspaper.get_id());

        }
    }

    public void deleteArticles() {
        if (tableNews.getSelectionModel().getSelectedItem() != null) {
            newsListViewModel.deleteArticles(tableNews.getSelectionModel().getSelectedItem());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Articles");
            alert.setHeaderText("Articles");
            alert.setContentText(newsListViewModel.getState().get().getError());
            alert.showAndWait();
        }else {
            getPrincipalController().errorAlert("Select a newspaper");
        }
    }

    public void getNbrArticles() {
        if (tableNews.getSelectionModel().getSelectedItem() != null) {
            newsListViewModel.getNbrArticles(tableNews.getSelectionModel().getSelectedItem().get_id());
            numberArticlesType.getItems().clear();
            numberArticlesType.getItems().addAll(newsListViewModel.getState().get().getNbrArticles());
        }else {
            getPrincipalController().errorAlert("Select a newspaper");
        }
    }
}
