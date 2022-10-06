package ui.pantallas.reader.appendArticle;

import domain.modelo.Reader;
import domain.modelo.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.pantallas.common.BasePantallaController;

import java.io.IOException;
import java.time.LocalDate;

public class ReaderAppendArticleController extends BasePantallaController {
    @FXML
    private  TableView<Reader> readersTable;
    @FXML
    private  TableColumn<Integer, Reader> idColumn;
    @FXML
    private  TableColumn<String, Reader> nameColum;
    @FXML
    private  TableColumn<LocalDate, Reader> dateColumn;
    @FXML
    private  TableView<Subscription> subscriptions;
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

    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();
    }
}
