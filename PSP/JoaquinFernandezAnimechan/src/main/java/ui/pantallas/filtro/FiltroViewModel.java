package ui.pantallas.filtro;

import domain.modelo.cards.CardsList;
import domain.servicios.ServiciosCartas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.Constantes;

import java.io.IOException;

public class FiltroViewModel {


    private final ServiciosCartas serviciosCartas;
    private final ObjectProperty<FiltroState> state;

    @Inject
    public FiltroViewModel(ServiciosCartas serviciosCartas) {
        this.serviciosCartas = serviciosCartas;
        state = new SimpleObjectProperty<>(new FiltroState(null, false, null));
    }

    public void load() {
        //no se que tan vien ta esto pero lo he puesto dos veces y es un porro
        state.setValue(new FiltroState(null, !state.get().cambio(),
                serviciosCartas.verTodasLasCartas().getOrNull()));
    }

    public ReadOnlyObjectProperty<FiltroState> getState() {
        return state;
    }


    public Either<String, CardsList> getCardsAtkRace(String nombre, String attack, String race, String sort){
        return serviciosCartas.getCardsAtkRace(nombre, Constantes.GT +attack, race, sort);
    }


}