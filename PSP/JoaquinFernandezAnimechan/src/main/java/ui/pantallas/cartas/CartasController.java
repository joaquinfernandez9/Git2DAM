package ui.pantallas.cartas;

import domain.modelo.cards.DataItem;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;

import java.io.IOException;

public class CartasController extends BasePantallaController {

    @FXML
    public TextField cardName;
    @FXML
    public TableView<DataItem> tablaCartas;
    @FXML
    public TableColumn<Integer, DataItem> idCol;
    @FXML
    public TableColumn<String, DataItem> nombreCol;
    @FXML
    public TableColumn<Integer, DataItem> lvlCol;
    @FXML
    public TableColumn<Integer, DataItem> atkCol;
    @FXML
    public TableColumn<Integer, DataItem> defCol;
    @FXML public Label coolstuffinc_price;
    @FXML public Label tcgplayer_price;
    @FXML public Label amazon_price;
    @FXML public Label ebay_price;
    @FXML public Label cardmarket_price;



    CartasViewModel cartasViewModel;
    @Inject
    CartasController(CartasViewModel cartasViewModel){
        this.cartasViewModel = cartasViewModel;
    }

    public void initialize(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lvlCol.setCellValueFactory(new PropertyValueFactory<>("level"));
        atkCol.setCellValueFactory(new PropertyValueFactory<>("atk"));
        defCol.setCellValueFactory(new PropertyValueFactory<>("def"));




    }

    @Override
    public void principalCargado() throws IOException {
        super.principalCargado();

        cartasViewModel.getState().addListener((observable, oldValue, newValue) -> {
            tablaCartas.getItems().clear();
            tablaCartas.getItems().addAll(newValue.getCardsList().getData());



        });



        cartasViewModel.load();
    }


    public void btnBuscar() throws IOException {
        cartasViewModel.verCartasName(cardName.getText());
    }
}
