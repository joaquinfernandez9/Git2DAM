package ui.pantallas.mazos;

import domain.modelo.cards.CardsList;
import domain.modelo.cards.DataItem;
import domain.servicios.ServiciosCartas;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

public class VerCartaViewModel {

    private final ServiciosCartas serviciosCartas;
    private final ObjectProperty<VerCartaState> state;

    @Inject
    public VerCartaViewModel(ServiciosCartas serviciosCartas) {
        this.serviciosCartas = serviciosCartas;
        state = new SimpleObjectProperty<>(new VerCartaState(null, false, null));
    }

    public void load() throws IOException {
        state.setValue(new VerCartaState(null, false, serviciosCartas.verTodasLasCartas()));
    }

    public ReadOnlyObjectProperty<VerCartaState> getState() {
        return state;
    }

    public CardsList verCartaNombre(String nombre) throws IOException {
        return serviciosCartas.verUnaCarta(nombre);
    }

}
