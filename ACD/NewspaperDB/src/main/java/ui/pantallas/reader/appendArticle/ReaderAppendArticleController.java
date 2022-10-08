package ui.pantallas.reader.appendArticle;

import domain.modelo.Reader;
import domain.modelo.Subscription;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.io.IOException;
import java.time.LocalDate;

public class ReaderAppendArticleController extends BasePantallaController {
    @FXML
    private MFXTextField idArticleText;
    @FXML
    private MFXTextField ratingText;
    @FXML
    private  TableView<Reader> readersTable;
    @FXML
    private  TableColumn<Integer, Reader> idColumn;
    @FXML
    private  TableColumn<String, Reader> nameColum;
    @FXML
    private  TableColumn<LocalDate, Reader> dateColumn;
    @FXML
    private  TableView<Subscription> subscriptionsTable;
    @FXML
    private  TableColumn<Integer, Subscription> idSubscription;
    @FXML
    private  TableColumn<Integer, Subscription> idReader;
    @FXML
    private  TableColumn<Integer, Subscription> idNewspaper;
    @FXML
    private  TableColumn<LocalDate, Subscription> singDate;
    @FXML
    private TableColumn<LocalDate, Subscription> cancellationDate;

    private final AppendArticleViewmodel viewmodel;

    @Inject
    public ReaderAppendArticleController(AppendArticleViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        idReader.setCellValueFactory(new PropertyValueFactory<>("idReader"));
        idSubscription.setCellValueFactory(new PropertyValueFactory<>("idSubscription"));
        idNewspaper.setCellValueFactory(new PropertyValueFactory<>("idNewspaper"));
        singDate.setCellValueFactory(new PropertyValueFactory<>("singDate"));
        cancellationDate.setCellValueFactory(new PropertyValueFactory<>("cancellationDate"));

        viewmodel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.getReaderList() != null) {
                readersTable.getItems().clear();
                readersTable.getItems().addAll(newValue.getReaderList());
            }
        });

        readersTable.getSelectionModel().selectedItemProperty()
                        .addListener((observable, oldValue, newValue) -> {
                            if (readersTable.getSelectionModel().getSelectedItem() != null){
                                subscriptionsTable.getItems().clear();
                                subscriptionsTable.getItems().addAll(newValue.getSubscriptions().getSubscriptionsList());
                            }
                        });
        viewmodel.reloadState();
    }

    @FXML
    private void addReadArticle(){
        viewmodel.appendArticle(readersTable.getSelectionModel().getSelectedItem().getId(),
                Integer.parseInt(idArticleText.getText()), Integer.parseInt(ratingText.getText()));
        viewmodel.reloadState();
    }
}
