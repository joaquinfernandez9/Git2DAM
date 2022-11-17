package ui.pantallas.newspaper.add;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import ui.pantallas.common.BasePantallaController;

import java.util.Date;

public class NewsAddController extends BasePantallaController {
    @FXML public TableView<Newspaper> tableNews;
    @FXML public TableColumn<Integer, Newspaper> idColumn;
    @FXML public TableColumn<String, Newspaper> nameColumn;
    @FXML public TableColumn<Date, Newspaper> dateColumn;


    private final NewsAddViewModel newsAddViewModel;
    @FXML private TextField nameLabel;
    @FXML private TextField idLabel;

    @Inject
    public NewsAddController(NewsAddViewModel newsAddViewModel) {
        this.newsAddViewModel = newsAddViewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("release_date"));


        newsAddViewModel.getState().addListener((observable, oldValue, newValue) ->{
            if (newValue.getNewspaperList()!=null){
                tableNews.getItems().clear();
                tableNews.getItems().addAll(newValue.getNewspaperList());
            }
        });
        newsAddViewModel.load();
    }


    @FXML
    private void add() {
        newsAddViewModel.addNewspaper(
                Integer.parseInt(idLabel.getText()), nameLabel.getText());
    }
}
