package ui.pantallas.cartas;

import dao.retrofit.cards.DataItem;
import domain.modelo.Carta;
import domain.modelo.ListaCartas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.common.BasePantallaController;
import ui.common.Constantes;

import java.util.List;


public class CartasController extends BasePantallaController {



    @FXML
    private TextField cardName;
    @FXML
    private TableView<Carta> tablaCartas;
    @FXML
    private TableColumn<Integer, Carta> idCol;
    @FXML
    private TableColumn<String, Carta> nombreCol;
    @FXML
    private TableColumn<Integer, Carta> lvlCol;
    @FXML
    private TableColumn<Integer, Carta> atkCol;
    @FXML
    private TableColumn<Integer, Carta> defCol;
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
            tablaCartas.getItems().addAll(newValue.cardsList().getCartas());
        });

        tablaCartas.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (tablaCartas.getSelectionModel().getSelectedItem() != null) {
                        lvPrecio.setText(tablaCartas.getSelectionModel().getSelectedItem().getPreciosCartas().toString());
                        // no se por que peta esto, es una imagen, si no lo consigo me sudala polla en vd
                        imageView.setImage(new Image(String.valueOf(tablaCartas.getSelectionModel().getSelectedItem()
                                .getListaImgCartas().get(0).getUrlImg())));
                    }
                });
        cartasViewModel.load();
    }

    @FXML
    private void btnBuscar() {
        if (cardName.getText().isEmpty()){
            getPrincipalController().sacarAlertError(Constantes.NO_SE_HA_PROPORCIONADO_UN_NOMBRE);
        } else {
            asyncTask();
//            cartasViewModel.verCartasName(cardName.getText());
//            cartasViewModel.load();
        }
    }


    private void asyncTask() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.getMessage();
        }
        var task = new Task<Either<String, ListaCartas>>() {
            @Override
            protected Either<String, ListaCartas> call() {
                return cartasViewModel.verCartasName(cardName.getText());
            }
        };
        task.setOnSucceeded(workerStateEvent -> {
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
            var result = task.getValue();
            result.peek(carta -> {
                tablaCartas.getItems().clear();
                tablaCartas.getItems().addAll(carta.getCartas());
            }).peekLeft(error -> getPrincipalController().sacarAlertError("error"));
        });
        task.setOnFailed(workerStateEvent -> {
            //workerStateEvent.getSource().getException().getMessage()
            getPrincipalController().sacarAlertError(task.getException().getMessage());
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        });

        new Thread(task).start();
    }


}
