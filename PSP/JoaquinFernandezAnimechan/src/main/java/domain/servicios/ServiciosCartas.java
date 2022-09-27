package domain.servicios;

import dao.DaoCartas;
import domain.modelo.cards.DataItem;
import domain.modelo.cards.CardsList;

import java.io.IOException;

public class ServiciosCartas {

    DaoCartas daoCartas = new DaoCartas();

    public CardsList verUnaCarta(String nombreCarta)throws IOException{
        return daoCartas.verUnaCarta(nombreCarta);
    }

    public CardsList verCartasName(String nombre) throws IOException {
        return daoCartas.verCartasConNombre(nombre);
    }

    public CardsList verTodasLasCartas() throws IOException {
        return daoCartas.verTodasLasCartas();
    }


    public CardsList getCardsAtkRace(String nombre, String attack, String race, String sort) throws IOException {
        return daoCartas.getCardsAtkRace(nombre,"gt"+attack,race,sort);
    }




}
