package ui.pantallas.article.addDeleteUpdate;

import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.enterprise.inject.New;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.Newspaper;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.util.Objects;

public class Controller extends BasePantallaController {
    public TableView<Article> articleTable;
    public TableColumn<String, Article> titleColumn;
    public TableColumn<String, Article> typeIDColumn;
    public TableView<Newspaper> npTable;
    public TableColumn<Integer, Newspaper> idTable;
    public TableColumn<String, Newspaper> nameTable;
    public TableColumn<LocalDate, Newspaper> releaseTableDate;

    public MFXTextField name;
    public MFXTextField type;

    private final ViewModel viewModel;

    @Inject
    public Controller(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void principalCargado(){
        super.principalCargado();
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeIDColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        idTable.setCellValueFactory(new PropertyValueFactory<>("_id"));
        nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        releaseTableDate.setCellValueFactory(new PropertyValueFactory<>("relDate"));

        npTable.getItems().clear();
        npTable.getItems().setAll(viewModel.loadNewspaper());

        npTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                articleTable.getItems().clear();
                articleTable.getItems().setAll(viewModel.loadArticle(newValue.get_id()));
            }
        });

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText(newValue.getError());
                alert.showAndWait();
                viewModel.nullState();
            }else if (newValue.getNewspapers() != null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Newspaper added");
                alert.setHeaderText("Newspaper added");
                alert.setContentText("Newspaper Modified");
                alert.showAndWait();
                viewModel.nullState();
            }else if (newValue.getArticles() != null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Article added");
                alert.setHeaderText("Article added");
                alert.setContentText("Article modified");
                alert.showAndWait();
                viewModel.nullState();
            }

        });

    }


    public void add() {
        Newspaper newspaper = npTable.getSelectionModel().getSelectedItem();
        viewModel.addArticle(new Article(name.getText(), type.getText()), newspaper);

    }

    public void delete() {
        Newspaper newspaper = npTable.getSelectionModel().getSelectedItem();
        Article article = articleTable.getSelectionModel().getSelectedItem();
        viewModel.deleteArticle(article, newspaper);
    }

    public void update() {
        Newspaper newspaper = npTable.getSelectionModel().getSelectedItem();
        Article article = articleTable.getSelectionModel().getSelectedItem();
        viewModel.updateArticle(new Article(name.getText(), type.getText()), newspaper, article.getType());
    }


}
