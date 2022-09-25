package ui.pantallas.cartas;

import domain.servicios.ServiciosCartas;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

public class CartasViewModel {

    private final ServiciosCartas serviciosCartas;
    private final ObjectProperty<CartasState> state;

    @Inject
    public CartasViewModel(ServiciosCartas serviciosCartas) {
        this.serviciosCartas = serviciosCartas;
        state = new SimpleObjectProperty<>(new CartasState(null, false, null));
    }

    public void load() throws IOException {
        state.setValue(new CartasState(null, !state.get().isCambio(), serviciosCartas.verTodasLasCartas()));
    }

    public ReadOnlyObjectProperty<CartasState> getState() {
        return state;
    }



    public void verCartasName(String name) throws IOException {
        serviciosCartas.verCartasName(name);
        state.setValue(new CartasState(null, !state.get().isCambio(), serviciosCartas.verCartasName(name)));
    }




}
