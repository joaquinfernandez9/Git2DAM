package ui.pantallas.unicaCarta;

import dao.retrofit.cards.DataItem;
import domain.modelo.Carta;
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

    public Either<String, Carta> verCartaNombre(String nombre){
            return serviciosCartas.verUnaCarta(nombre);
    }

    public Either<String, Carta> cartaRandom(){
        return serviciosCartas.cartaRandom();
    }

}
