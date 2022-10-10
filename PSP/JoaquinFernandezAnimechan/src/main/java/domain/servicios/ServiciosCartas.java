package domain.servicios;

import dao.retrofit.cards.CardsList;
import dao.retrofit.cards.DataItem;
import domain.modelo.Carta;
import domain.modelo.ListaCartas;
import io.vavr.control.Either;

public interface ServiciosCartas {
    Either<String, Carta> verUnaCarta(String nombreCarta);

    Either<String, ListaCartas> verCartasName(String nombre);

    Either<String, ListaCartas> verTodasLasCartas();

    Either<String, ListaCartas> getCardsAtkRace(String nombre, String attack, String race, String sort);
}
