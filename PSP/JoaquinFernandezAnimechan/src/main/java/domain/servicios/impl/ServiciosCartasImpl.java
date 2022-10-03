package domain.servicios.impl;

import dao.DaoCartas;
import domain.modelo.cards.DataItem;
import domain.modelo.cards.CardsList;
import domain.servicios.ServiciosCartas;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class ServiciosCartasImpl implements ServiciosCartas {

    private final DaoCartas daoCartas;

    @Inject
    public ServiciosCartasImpl(DaoCartas daoCartas) {
        this.daoCartas = daoCartas;
    }

    @Override
    public Either<String, DataItem> verUnaCarta(String nombreCarta){
        return daoCartas.verUnaCarta(nombreCarta);
    }

    @Override
    public Either<String, CardsList> verCartasName(String nombre){
        return daoCartas.verCartasConNombre(nombre);
    }

    @Override
    public Either<String, CardsList> verTodasLasCartas(){
            return daoCartas.verTodasLasCartas();
    }

    @Override
    public Either<String, CardsList> getCardsAtkRace(String nombre, String attack, String race, String sort) {
        return daoCartas.getCardsAtkRace(nombre,attack,race,sort);
    }

}
