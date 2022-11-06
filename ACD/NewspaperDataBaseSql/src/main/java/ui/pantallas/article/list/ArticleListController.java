package ui.pantallas.article.list;

import model.Article;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.io.IOException;

public class ArticleListController extends BasePantallaController {

    @FXML
    public TableView<Article> tableArticle;
    @FXML
    public TableColumn<Integer, Article> idColumn;
    @FXML
    public TableColumn<String, Article> titleColumn;
    @FXML
    public TableColumn<String, Article> authorColumn;
    @FXML
    public TableColumn<String, Article> npID;
    @FXML
    public TableColumn<String, Article> typeID;


    @FXML
    public MFXComboBox<String> combo;

    private final ArticleListViewModel articleListViewModel;

    @Inject
    public ArticleListController(ArticleListViewModel articleListViewModel) {
        this.articleListViewModel = articleListViewModel;
    }

    @Override
    public void principalCargado() {
        combo.getItems().addAll("Sports", "Science");


        idColumn.setCellValueFactory(new PropertyValueFactory<>("articleID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        npID.setCellValueFactory(new PropertyValueFactory<>("newspaperID"));
        typeID.setCellValueFactory(new PropertyValueFactory<>("typeID"));


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
            articleListViewModel.getAllfilter(combo.getValue());
        } else {
            getPrincipalController().sacarAlertError(UiConstants.ERROR);
        }
    }
}
