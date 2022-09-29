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
//        state = new SimpleObjectProperty<>(new VerCartaState(null, false, null));
    }

//    public void load() throws IOException {
//        state.setValue(new VerCartaState(null, false, serviciosCartas.verTodasLasCartas()));
//    }
//
//    public ReadOnlyObjectProperty<VerCartaState> getState() {
//        return state;
//    }

    public Either<String, DataItem> verCartaNombre(String nombre){
        if (serviciosCartas.verUnaCarta(nombre).isRight()){
            return serviciosCartas.verUnaCarta(nombre);
        } else {
            return Either.left("No hay cartas");
        }
    }

}
