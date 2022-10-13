package domain.servicios.impl;

import dao.DaoCartas;
import domain.modelo.Carta;
import domain.modelo.ListaCartas;
import domain.modelo.ListaSetsCarta;
import domain.servicios.ServiciosCartas;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class ServiciosCartasImpl implements ServiciosCartas {

    private final DaoCartas daoCartas;

    @Inject
    public ServiciosCartasImpl(DaoCartas daoCartas) {
        this.daoCartas = daoCartas;
    }

    @Override
    public Single<Either<String, Carta>> verUnaCarta(String nombreCarta){
        return daoCartas.verUnaCarta(nombreCarta);
    }

    @Override
    public Either<String, Carta> cartaRandom(){
        return daoCartas.cartaRandom();
    }

    @Override
    public Either<String, List<ListaSetsCarta>> getAllCardSets(){
        return daoCartas.getAllCardSets();
    }

    @Override
    public Either<String, ListaCartas> verCartasName(String nombre){
        return daoCartas.verCartasConNombre(nombre);
    }

    @Override
    public Either<String, ListaCartas> verTodasLasCartas(){
            return daoCartas.verTodasLasCartas();
    }

    @Override
    public Either<String, ListaCartas> getCardsAtkRace(String nombre, String attack, String race, String sort) {
        return daoCartas.getCardsAtkRace(nombre,attack,race,sort);
    }

}
