package dao;

import domain.modelo.cards.CardsList;
import domain.modelo.cards.DataItem;
import io.vavr.control.Either;

public interface DaoCartas {
    Either<String, DataItem> verUnaCarta(String nombre);

    Either<String, CardsList> verCartasConNombre(String nombre);

    Either<String, CardsList> verTodasLasCartas();

    Either<String, CardsList> getCardsAtkRace(String nombre, String atk, String race, String sort);
}
