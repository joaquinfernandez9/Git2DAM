package ui.pantallas.reader.appendArticle;

import javafx.scene.control.Alert;
import model.Article;
import model.ReadArticle;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

public class AppendArticleController extends BasePantallaController {
    private final AppendArticleViewmodel viewmodel;
    @FXML
    private MFXTextField ratingText;


    //aqui empieza lo bueno
    @FXML
    private TableView<Article> tableArticle;
    @FXML
    private TableColumn<Integer, Article> idArticle;
    @FXML
    private TableColumn<String, Article> nameArticle;
    @FXML
    private TableColumn<Integer, Article> typeID;
    @FXML
    private TableColumn<Integer, Article> newspaperID;
    @FXML
    private TableView<ReadArticle> tableReadArticle;
    @FXML
    private TableColumn<Integer, ReadArticle> articleID;
    @FXML
    private TableColumn<Integer, ReadArticle> ranking;

    @Inject
    public AppendArticleController(AppendArticleViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

       idArticle.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameArticle.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        typeID.setCellValueFactory(new PropertyValueFactory<>("id_type"));
        newspaperID.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));


        articleID.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        ranking.setCellValueFactory(new PropertyValueFactory<>("ranking"));



        viewmodel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getArticleList() != null) {
                tableArticle.getItems().clear();
                tableArticle.getItems().addAll(newValue.getArticleList());
            }
            if (newValue.getReadArticleList() != null) {
                tableReadArticle.getItems().clear();
                tableReadArticle.getItems().addAll(newValue.getReadArticleList());
            }
            if (newValue.getError() != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correct");
                alert.setContentText(newValue.getError());
                alert.showAndWait();
            }
            if (newValue.getError() != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText(newValue.getError());
                alert.showAndWait();
            }
        });

        viewmodel.reloadState(getPrincipalController().r.getId());
    }

    @FXML
    private void addReadArticle() {
        if (tableArticle.getSelectionModel().getSelectedItem() == null ||  ratingText.getText().isEmpty()) {
            getPrincipalController().errorAlert(UiConstants.NOT_FOUND);
        } else {
            //pillar reader
            viewmodel.appendArticle(getPrincipalController().r,
                    tableArticle.getSelectionModel().getSelectedItem().getId(),
                    Integer.parseInt(ratingText.getText()));

            viewmodel.reloadState(getPrincipalController().r.getId());
        }
    }
}
