package ui.pantallas.article.add;

import model.Article;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.io.IOException;

public class ArticleAddController extends BasePantallaController {

    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Integer, Article> idColumn;
    @FXML
    private TableColumn<String, Article> titleColumn;
    @FXML
    private TableColumn<String, Article> authorColumn;
    @FXML
    private TableColumn<Integer, Article> npIDColumn;
    @FXML
    private TableColumn<Integer, Article> typeIDColumn;

    private final ArticleAddViewModel articleAddViewModel;

    @Inject
    public ArticleAddController(ArticleAddViewModel articleAddViewModel) {
        this.articleAddViewModel = articleAddViewModel;
    }

    @Override
    public void principalCargado() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("articleID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        npIDColumn.setCellValueFactory(new PropertyValueFactory<>("newspaperID"));
        typeIDColumn.setCellValueFactory(new PropertyValueFactory<>("typeID"));

        super.principalCargado();

        articleAddViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getArticleList() != null) {
                articleTable.getItems().clear();
                articleTable.getItems().addAll(newValue.getArticleList());
            }
        });

        articleAddViewModel.load();

    }

    @FXML
    private MFXTextField articleID;
    @FXML
    private MFXTextField title;
    @FXML
    private MFXTextField author;
    @FXML
    private MFXTextField newspaperID;
    @FXML
    private MFXTextField typeID;

    @FXML
    private void add(ActionEvent actionEvent) {
        if (articleID.getText().isEmpty() || title.getText().isEmpty()||
        author.getText().isEmpty()||newspaperID.getText().isEmpty() ||
        typeID.getText().isEmpty()){
            getPrincipalController().sacarAlertError(UiConstants.ERROR_COMPLETE_ALL_THE_FIELDS);
        } else {
            Article a  = new Article(Integer.parseInt(articleID.getText())+ UiConstants.DOT_COMMA
                    +title.getText()+ UiConstants.DOT_COMMA +author.getText()+ UiConstants.DOT_COMMA
                    +Integer.parseInt(newspaperID.getText())+ UiConstants.DOT_COMMA
                    +Integer.parseInt(typeID.getText()));
            articleAddViewModel.add(a);
        }

    }
}
