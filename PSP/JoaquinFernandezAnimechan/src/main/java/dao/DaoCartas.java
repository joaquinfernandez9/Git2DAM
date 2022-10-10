package dao;

import dao.retrofit.cards.CardsList;
import dao.retrofit.cards.DataItem;
import domain.modelo.Carta;
import domain.modelo.ListaCartas;
import io.vavr.control.Either;

public interface DaoCartas {
    Either<String, Carta> verUnaCarta(String nombre);

    Either<String, ListaCartas> verCartasConNombre(String nombre);

    Either<String, ListaCartas> verTodasLasCartas();

    Either<String, ListaCartas> getCardsAtkRace(String nombre, String atk, String race, String sort);
}
