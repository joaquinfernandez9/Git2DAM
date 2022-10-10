package ui.pantallas.sets;

import domain.modelo.ListaSetsCarta;
import domain.servicios.ServiciosCartas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.cartas.CartasState;

import java.util.List;

public class SetsViewModel {
    private final ServiciosCartas serviciosCartas;
    private final ObjectProperty<SetsState> state;

    @Inject
    public SetsViewModel(ServiciosCartas serviciosCartas) {
        this.serviciosCartas = serviciosCartas;
        state = new SimpleObjectProperty<>(
                new SetsState(null, false, null));
    }

    public void reloadState(){
        state.setValue(new SetsState(null, !state.get().cambio(),
                serviciosCartas.getAllCardSets().get()));
    }

    public ReadOnlyObjectProperty<SetsState> getState() {
        return state;
    }



}
