package ui.pantallas.unicaCarta;

import domain.modelo.cards.CardsList;
import domain.modelo.cards.DataItem;
import domain.servicios.ServiciosCartas;
import io.vavr.control.Either;
import jakarta.inject.Inject;


public class VerCartaViewModel {

    private final ServiciosCartas serviciosCartas;
//    private final ObjectProperty<VerCartaState> state;

    @Inject
    public VerCartaViewModel(ServiciosCartas serviciosCartas) {
        this.serviciosCartas = serviciosCartas;
    }

    public Either<String, DataItem> verCartaNombre(String nombre){
            return serviciosCartas.verUnaCarta(nombre);
    }

}
