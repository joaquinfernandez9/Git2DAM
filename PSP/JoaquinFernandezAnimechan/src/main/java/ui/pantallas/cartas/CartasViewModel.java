package ui.pantallas.cartas;

import domain.modelo.ListaCartas;
import domain.servicios.ServiciosCartas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;



public class CartasViewModel {
    private final ServiciosCartas serviciosCartas;
    private final ObjectProperty<CartasState> state;

    @Inject
    public CartasViewModel(ServiciosCartas serviciosCartas) {
        this.serviciosCartas = serviciosCartas;
        state = new SimpleObjectProperty<>(
                new CartasState(null, false, null));
    }

    public void load(){
        state.setValue(new CartasState(null,
                !state.get().cambio(),
                serviciosCartas.verTodasLasCartas().getOrNull()));
    }

    public ReadOnlyObjectProperty<CartasState> getState() {
        return state;
    }

    public Either<String, ListaCartas> verCartasName(String name){
        return serviciosCartas.verCartasName(name);
    }

}
