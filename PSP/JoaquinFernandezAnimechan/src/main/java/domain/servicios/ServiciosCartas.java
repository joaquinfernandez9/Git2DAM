package domain.servicios;

import domain.modelo.cards.CardsList;
import domain.modelo.cards.DataItem;
import io.vavr.control.Either;

public interface ServiciosCartas {
    Either<String, DataItem> verUnaCarta(String nombreCarta);

    Either<String, CardsList> verCartasName(String nombre);

    Either<String, CardsList> verTodasLasCartas();

    Either<String, CardsList> getCardsAtkRace(String nombre, String attack, String race, String sort);
}
