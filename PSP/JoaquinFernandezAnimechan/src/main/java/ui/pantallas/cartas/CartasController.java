package ui.pantallas.cartas;

import domain.modelo.cards.CardPricesItem;
import domain.modelo.cards.DataItem;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.common.BasePantallaController;
import ui.common.Constantes;


public class CartasController extends BasePantallaController {



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
    private ImageView imageView;

    private final CartasViewModel cartasViewModel;

    @Inject
    CartasController(CartasViewModel cartasViewModel) {
        this.cartasViewModel = cartasViewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();

        //no se que hacer con el codigo duplicado aqui
        idCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.NAME));
        lvlCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.LEVEL));
        atkCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.ATK));
        defCol.setCellValueFactory(new PropertyValueFactory<>(Constantes.DEF));

        cartasViewModel.getState().addListener((observable, oldValue, newValue) -> {
            tablaCartas.getItems().clear();
            tablaCartas.getItems().addAll(newValue.cardsList().getData());
        });

        tablaCartas.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (tablaCartas.getSelectionModel().getSelectedItem() != null) {
                        lvPrecio.setText(tablaCartas.getSelectionModel().getSelectedItem().getCard_prices().toString());
                        imageView.setImage(new Image(tablaCartas.getSelectionModel().getSelectedItem()
                                .getCard_images().get(0).getImage_url()));
                    }
                });
        cartasViewModel.load();
    }

    @FXML
    private void btnBuscar() {
        if (cardName.getText().isEmpty()){
            getPrincipalController().sacarAlertError(Constantes.NO_SE_HA_PROPORCIONADO_UN_NOMBRE);
        } else if (cartasViewModel.verCartasName(cardName.getText()).isRight()){
            cartasViewModel.verCartasName(cardName.getText());
            cartasViewModel.load();
        } else {
            getPrincipalController().sacarAlertError(Constantes.NO_HAY_CARTAS_CON_ESE_NOMBRE);
        }
    }


}
