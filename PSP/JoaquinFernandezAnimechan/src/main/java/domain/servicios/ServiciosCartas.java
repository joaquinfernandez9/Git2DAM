package domain.servicios;

import domain.modelo.Carta;
import domain.modelo.ListaCartas;
import domain.modelo.ListaSetsCarta;
import io.vavr.control.Either;

import java.util.List;

public interface ServiciosCartas {
    Either<String, Carta> verUnaCarta(String nombreCarta);

    Either<String, Carta> cartaRandom();

    Either<String, List<ListaSetsCarta>> getAllCardSets();

    void verCartasName(String nombre);

    Either<String, ListaCartas> verTodasLasCartas();

    Either<String, ListaCartas> getCardsAtkRace(String nombre, String attack, String race, String sort);
}
