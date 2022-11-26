package ui.pantallas.querys;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

public class QueryController extends BasePantallaController {

    private final QueryViewModel viewModel;
    @FXML
    private TextField queryDescription;
    @FXML
    private TextField queryNewspaper;
    @FXML
    private TableView<QueryArticlesNewspaper> query3;
    @FXML
    private TableColumn<String, QueryArticlesNewspaper> query3Col1;
    @FXML
    private TableColumn<String, QueryArticlesNewspaper> query3Col2;
    @FXML
    private TableView<QueryArticleRating> query4;
    @FXML
    private TableColumn<String, QueryArticleRating> query4Col1;
    @FXML
    private TableColumn<Integer, QueryArticleRating> query4Col2;
    @FXML
    private TableColumn<Integer, QueryArticleRating> query4Col3;

    @Inject
    public QueryController(QueryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        query3Col1.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_ARTICLE));
        query3Col2.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_NEWSPAPER));

        query4Col1.setCellValueFactory(new PropertyValueFactory<>(UiConstants.NAME_ARTICLE));
        query4Col2.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        query4Col3.setCellValueFactory(new PropertyValueFactory<>(UiConstants.BAD_RATINGS));

        Platform.runLater(viewModel::load);
        //falla en el errorAlert y no comprendo por que
        viewModel.getState().addListener((observable, oldState, newState) -> {
            if (newState.getThirdQuery() != null) {
                Platform.runLater(()->{
                    query3.getItems().clear();
                    query3.getItems().addAll(newState.getThirdQuery());
                });
            }
            if (newState.getFourthQuery() != null) {
                Platform.runLater(() -> {
                    query4.getItems().clear();
                    query4.getItems().addAll(newState.getFourthQuery());
                });
            }
            if (newState.getError() != null) {
                Platform.runLater(() -> getPrincipalController().errorAlert(newState.getError()));
            }
        });

    }

    @FXML
    private void query3() {
        if (queryDescription.getText().isEmpty()) {
            getPrincipalController().errorAlert(UiConstants.FILL_FIELD);
        } else {
            viewModel.thirdQuery(queryDescription.getText());
        }
    }

    @FXML
    private void query4() {
        if (queryNewspaper.getText().isEmpty()) {
            getPrincipalController().errorAlert(UiConstants.FILL_FIELD);
        } else {
            viewModel.fourthQuery(Integer.parseInt(queryNewspaper.getText()));
        }
    }

}
