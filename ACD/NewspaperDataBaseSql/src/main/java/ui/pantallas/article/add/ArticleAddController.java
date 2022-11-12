package ui.pantallas.article.add;

import model.Article;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

public class ArticleAddController extends BasePantallaController {

    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Integer, Article> idColumn;
    @FXML
    private TableColumn<String, Article> titleColumn;
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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        npIDColumn.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));
        typeIDColumn.setCellValueFactory(new PropertyValueFactory<>("id_type"));

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
    private void add() {
        if (articleID.getText().isEmpty() || title.getText().isEmpty()||
        newspaperID.getText().isEmpty() ||
        typeID.getText().isEmpty()){
            getPrincipalController().errorAlert(UiConstants.ERROR_COMPLETE_ALL_THE_FIELDS);
        } else {
            Article a  = new Article(Integer.parseInt(articleID.getText()), title.getText(), Integer.parseInt(newspaperID.getText()), Integer.parseInt(typeID.getText()));
            articleAddViewModel.add(a);
        }

    }
}
