package ui.pantallas.cartas;

import domain.modelo.cards.CardPricesItem;
import domain.modelo.cards.DataItem;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.common.BasePantallaController;


public class CartasController extends BasePantallaController {

    CartasViewModel cartasViewModel;
    @FXML
    private TextField cardName;
    @FXML
    private TableView<DataItem> tablaCartas;
    @FXML
    private TableColumn<Integer, DataItem> idCol;
    @FXML
    private TableColumn<String, DataItem> nombreCol;
    @FXML
    private TableColumn<Integer, DataItem> lvlCol;
    @FXML
    private TableColumn<Integer, DataItem> atkCol;
    @FXML
    private TableColumn<Integer, DataItem> defCol;
    @FXML
    private Label lvPrecio;
    @FXML
    private Label coolstuffinc_price;
    @FXML
    private Label tcgplayer_price;
    @FXML
    private Label amazon_price;
    @FXML
    private Label ebay_price;
    @FXML
    private Label cardmarket_price;

    @Inject
    CartasController(CartasViewModel cartasViewModel) {
        this.cartasViewModel = cartasViewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lvlCol.setCellValueFactory(new PropertyValueFactory<>("level"));
        atkCol.setCellValueFactory(new PropertyValueFactory<>("atk"));
        defCol.setCellValueFactory(new PropertyValueFactory<>("def"));

        cartasViewModel.getState().addListener((observable, oldValue, newValue) -> {
            tablaCartas.getItems().clear();
            tablaCartas.getItems().addAll(newValue.cardsList().getData());


        });

        tablaCartas.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (tablaCartas.getSelectionModel().getSelectedItem() != null) {

                        lvPrecio.setText(tablaCartas.getSelectionModel().getSelectedItem().getCard_prices().toString());
                    }
                });


        cartasViewModel.load();
    }


    @FXML
    private void btnBuscar() {
        cartasViewModel.verCartasName(cardName.getText());
    }


}
