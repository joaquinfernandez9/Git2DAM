package ui.pantallas.reader.subscribe;

import jakarta.enterprise.inject.New;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import model.Subscription;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.util.Date;

public class SubscribeController extends BasePantallaController {
    private final SubscribeViewModel viewModel;
    @FXML
    private TableView<Newspaper> tableNewspaper;
    @FXML
    private TableColumn<Integer, Newspaper> idNewspaper;
    @FXML
    private TableColumn<String, Newspaper> nameNewspaper;
    @FXML
    private TableColumn<LocalDate, Newspaper> rdNewspaper;

    @FXML
    private TableView<Subscription> tableSubscriptions;
    @FXML
    private TableColumn<Integer, Subscription> idNewspaperSubscription;
    @FXML
    private TableColumn<LocalDate, Subscription> singDate;
    @FXML
    private TableColumn<LocalDate, Subscription> cancelDate;

    @Inject
    public SubscribeController(SubscribeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void principalCargado() {
        super.principalCargado();

        idNewspaper.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameNewspaper.setCellValueFactory(new PropertyValueFactory<>("name_newspaper"));
        rdNewspaper.setCellValueFactory(new PropertyValueFactory<>("release_date"));

        idNewspaperSubscription.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));
        singDate.setCellValueFactory(new PropertyValueFactory<>("sing_date"));
        cancelDate.setCellValueFactory(new PropertyValueFactory<>("cancellation_date"));

        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            tableNewspaper.getItems().clear();
            tableNewspaper.getItems().addAll(newValue.getNewspaperList());

            tableSubscriptions.getItems().clear();
            tableSubscriptions.getItems().addAll(newValue.getSubscriptionList());

        });

        viewModel.reloadState(getPrincipalController().r.getId());


    }


    @FXML
    private void unsubscribe() {
        viewModel.unsubscribe(tableSubscriptions.getSelectionModel().getSelectedItem().getId_newspaper());
    }

    @FXML
    private void subscribe() {
        viewModel.subscribe(getPrincipalController().r.getId(),
                tableNewspaper.getSelectionModel().getSelectedItem().getId(),
                LocalDate.now(), null);
    }
}
