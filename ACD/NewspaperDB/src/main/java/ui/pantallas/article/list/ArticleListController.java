package ui.pantallas.article.list;

import domain.modelo.Article;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

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

    private final ArticleListViewModel articleListViewModel;

    @FXML
    public MFXComboBox<String> combo;

    @Inject
    public ArticleListController(ArticleListViewModel articleListViewModel) {
        this.articleListViewModel = articleListViewModel;
    }

    @Override
    public void principalCargado() throws IOException {
        combo.getItems().addAll("Sport", "Science", "Metro");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("articleID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        npID.setCellValueFactory(new PropertyValueFactory<>("newspaperID"));
        typeID.setCellValueFactory(new PropertyValueFactory<>("typeID"));


        super.principalCargado();

//        articleListViewModel.getState().addListener((observable, oldValue, newValue) -> {
//            if (newValue.getArticleList() != null) {
//                tableArticle.getItems().clear();
//                tableArticle.getItems().addAll(newValue.getArticleList());
//            }
//        });

        articleListViewModel.load();
    }


    @FXML
    private void filterBtn() {
        tableArticle.getItems().clear();
        tableArticle.getItems().addAll(articleListViewModel.getAllfilter(combo.getValue()));
    }
}
