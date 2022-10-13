package ui.pantallas.unicaCarta;

import domain.modelo.Carta;
import domain.servicios.ServiciosCartas;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.concurrent.TimeUnit;


public class VerCartaViewModel {

    private final ServiciosCartas serviciosCartas;
    private final ObjectProperty<VerCartaState> _state;


    @Inject
    public VerCartaViewModel(ServiciosCartas serviciosCartas) {
        this.serviciosCartas = serviciosCartas;

        _state = new SimpleObjectProperty<>(new VerCartaState(null, false, null));

    }

    public ReadOnlyObjectProperty<VerCartaState> getState(){
        return _state;
    }

    public void verCartaNombre(String nombre){
            serviciosCartas.verUnaCarta(nombre)
                    .delay(2, TimeUnit.SECONDS)
                    .observeOn(Schedulers.single())
                    .subscribe(either -> {
                        VerCartaState state;
                        if (either.isLeft()){
                            state = new VerCartaState(either.getLeft(), !_state.get().cambio(), null);
                        } else {
                            state = new VerCartaState(null, !_state.get().cambio(), either.get());
                        }
                        _state.setValue(state);
                    });
    }

    public Either<String, Carta> cartaRandom(){
        return serviciosCartas.cartaRandom();
    }

}

