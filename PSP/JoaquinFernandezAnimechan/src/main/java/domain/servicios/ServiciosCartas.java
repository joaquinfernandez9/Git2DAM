package domain.servicios;

import dao.DaoCartas;
import domain.modelo.cards.CardPricesItem;
import domain.modelo.cards.DataItem;
import domain.modelo.cards.CardsList;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ServiciosCartas {

    private final DaoCartas daoCartas;

    @Inject
    public ServiciosCartas(DaoCartas daoCartas) {
        this.daoCartas = daoCartas;
    }


    public Either<String, DataItem> verUnaCarta(String nombreCarta){
        return daoCartas.verUnaCarta(nombreCarta);
    }

    public Either<String, CardsList> verCartasName(String nombre){
        return daoCartas.verCartasConNombre(nombre);
    }

    public Either<String, CardsList> verTodasLasCartas(){
        if (daoCartas.verTodasLasCartas().isRight()){
            return daoCartas.verTodasLasCartas();
        } else {
            return Either.left(daoCartas.verTodasLasCartas().getLeft());
        }
    }

    public Either<String, CardsList> getCardsAtkRace(String nombre, String attack, String race, String sort) {
        return daoCartas.getCardsAtkRace(nombre,"gt"+attack,race,sort);
    }

    public Either<String, List<CardPricesItem>> verValorCarta(String nombre){
        return daoCartas.verValorCarta(nombre);
    }




}
