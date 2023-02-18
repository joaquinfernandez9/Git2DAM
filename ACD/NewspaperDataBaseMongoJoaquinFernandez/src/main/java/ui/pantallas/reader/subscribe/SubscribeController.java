package ui.pantallas.reader.subscribe;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import model.Subscription;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.UiConstants;

import java.time.LocalDate;

public class SubscribeController extends BasePantallaController {
    public static final String NAME_NEWSPAPER = "name_newspaper";
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

    @Override
    public void principalCargado() {
        super.principalCargado();


        idNewspaper.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID));
        nameNewspaper.setCellValueFactory(new PropertyValueFactory<>(NAME_NEWSPAPER));
        rdNewspaper.setCellValueFactory(new PropertyValueFactory<>(UiConstants.RELEASE_DATE));

        idNewspaperSubscription.setCellValueFactory(new PropertyValueFactory<>(UiConstants.ID_NEWSPAPER));
        singDate.setCellValueFactory(new PropertyValueFactory<>(UiConstants.SING_DATE));
        cancelDate.setCellValueFactory(new PropertyValueFactory<>(UiConstants.CANCELLATION_DATE));

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
        if (tableSubscriptions.getSelectionModel().getSelectedItem() != null) {
            viewModel.unsubscribe(getPrincipalController().r.getId(), tableSubscriptions.getSelectionModel().getSelectedItem().getId_newspaper());
        } else {
            getPrincipalController().errorAlert(UiConstants.NO_SUBSCRIPTION_SELECTED);
        }
    }

    @FXML
    private void subscribe() {
        viewModel.subscribe(getPrincipalController().r.getId(),
                tableNewspaper.getSelectionModel().getSelectedItem().get_id(),
                LocalDate.now(), null);
        viewModel.reloadState(getPrincipalController().r.getId());
    }
}
