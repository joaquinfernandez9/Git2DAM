package ui.pantallas.unicaCarta;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.pdfsam.rxjavafx.schedulers.JavaFxScheduler;
import ui.common.BasePantallaController;
import ui.common.Constantes;

public class VerCartaController extends BasePantallaController {


    private final VerCartaViewModel verCartaViewModel;
    @FXML
    private TextField nombre;
    @FXML
    private Label mostrarCarta;

    @Inject
    VerCartaController(VerCartaViewModel verCartaViewModel) {
        this.verCartaViewModel = verCartaViewModel;
    }

    @Override
    public void principalCargado(){
        super.principalCargado();
        verCartaViewModel.getState().addListener((observableValue, cromo, cromoNew) -> {
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
            Platform.runLater(() ->
                    mostrarCarta.setText(verCartaViewModel.getState().get().carta().toString()));
                }

        );
    }


    @FXML
    private void buscar() {
        if (nombre.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(Constantes.ERROR);
            alert.setHeaderText(Constantes.NO_SE_HA_PROPORCIONADO_UN_NOMBRE);
            alert.showAndWait();
            asyncSingle();
        } else {
            getPrincipalController().root.setCursor(Cursor.WAIT);
            verCartaViewModel.verCartaNombre(nombre.getText());
        }
    }


    private void asyncSingle() {
        Single.fromCallable(verCartaViewModel::cartaRandom)
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .doFinally(() -> getPrincipalController().root.setCursor(Cursor.DEFAULT))
                .subscribe(result ->
                                result.peek(carta -> mostrarCarta.setText(verCartaViewModel
                                                .cartaRandom()
                                                .get().toString()))
                                        .peekLeft(error -> getPrincipalController().sacarAlertError(Constantes.ERROR)),
                        throwable -> getPrincipalController().sacarAlertError(throwable.getMessage()));
        getPrincipalController().root.setCursor(Cursor.WAIT);
    }




}
