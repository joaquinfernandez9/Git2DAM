package ui.pantallas.article.update;

import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import ui.pantallas.common.BasePantallaController;

public class ArticleUpdateController extends BasePantallaController {

    @FXML
    private MFXTextField nameArticle;
    @FXML
    private MFXTextField newspaperID;
    @FXML
    private MFXTextField typeID;
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

    private final ArticleUpdateViewModel viewModel;

    @Inject
    public ArticleUpdateController(ArticleUpdateViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        npIDColumn.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));
        typeIDColumn.setCellValueFactory(new PropertyValueFactory<>("id_type"));

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getArticleList() != null) {
                articleTable.getItems().clear();
                articleTable.getItems().addAll(newValue.getArticleList());
            }
        });
        viewModel.load();
    }


    @FXML private void update() {
        viewModel.update(articleTable.getSelectionModel().getSelectedItem().getId(),
                nameArticle.getText(), Integer.parseInt(typeID.getText()),
                Integer.parseInt(newspaperID.getText()));

    }
}
