package ui.pantallas.filtro;

import dao.retrofit.cards.CardsList;
import domain.modelo.ListaCartas;
import domain.servicios.ServiciosCartas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.Constantes;

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
                serviciosCartas.verTodasLasCartas().get()));
    }

    public ReadOnlyObjectProperty<FiltroState> getState() {
        return state;
    }


    public boolean getCardsAtkRace(String nombre, String attack, String race, String sort){
        if (serviciosCartas.getCardsAtkRace(nombre, Constantes.GT +attack, race, sort).isRight()){
            state.setValue(new FiltroState(null, !state.get().cambio(),
                    serviciosCartas.getCardsAtkRace(nombre, Constantes.GT +attack, race, sort).get()));
            return true;
        } else return false;
    }


}
