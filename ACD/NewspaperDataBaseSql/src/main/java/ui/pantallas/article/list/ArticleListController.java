package ui.pantallas.article.list;

import model.Article;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ArticleType;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

public class ArticleListController extends BasePantallaController {

    @FXML
    public TableView<Article> tableArticle;
    @FXML
    public TableColumn<Integer, Article> idColumn;
    @FXML
    public TableColumn<String, Article> titleColumn;
    @FXML
    public TableColumn<String, Article> npID;
    @FXML
    public TableColumn<String, Article> typeID;


    @FXML
    public MFXComboBox<ArticleType> combo;

    private final ArticleListViewModel articleListViewModel;

    @Inject
    public ArticleListController(ArticleListViewModel articleListViewModel) {
        this.articleListViewModel = articleListViewModel;
    }

    @Override
    public void principalCargado() {

        combo.getItems().addAll(articleListViewModel.getAll());


        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        npID.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));
        typeID.setCellValueFactory(new PropertyValueFactory<>("id_type"));


        super.principalCargado();

        articleListViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getArticleList() != null) {
                tableArticle.getItems().clear();
                tableArticle.getItems().addAll(newValue.getArticleList());
            }
        });

        articleListViewModel.reloadState();
    }


    @FXML
    private void filterBtn() {
        if (combo.getValue() != null){
            articleListViewModel.getAllfilter(String.valueOf(combo.getValue()));
        } else {
            getPrincipalController().errorAlert(UiConstants.ERROR);
        }
    }
}
