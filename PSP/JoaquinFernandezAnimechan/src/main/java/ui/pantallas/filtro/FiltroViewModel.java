package ui.pantallas.filtro;

import domain.modelo.cards.CardsList;
import domain.servicios.ServiciosCartas;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

public class FiltroViewModel {

    private final ServiciosCartas serviciosCartas;
    private final ObjectProperty<FiltroState> state;

    @Inject
    public FiltroViewModel(ServiciosCartas serviciosCartas) {
        this.serviciosCartas = serviciosCartas;
        state = new SimpleObjectProperty<>(new FiltroState(null, false, null));
    }

    public void load() throws IOException {
        state.setValue(new FiltroState(null, !state.get().isCambio(), serviciosCartas.verTodasLasCartas()));
    }

    public ReadOnlyObjectProperty<FiltroState> getState() {
        return state;
    }


    public CardsList getCardsAtkRace(String nombre, String attack, String race, String sort) throws IOException {
        state.setValue(new FiltroState(null, !state.get().isCambio(), serviciosCartas.getCardsAtkRace(nombre,attack,race,sort)));
        return serviciosCartas.getCardsAtkRace(nombre, attack, race, sort);
    }


}
