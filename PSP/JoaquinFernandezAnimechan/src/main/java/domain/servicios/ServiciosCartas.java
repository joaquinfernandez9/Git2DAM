package domain.servicios;

import domain.modelo.Carta;
import domain.modelo.ListaCartas;
import domain.modelo.ListaSetsCarta;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface ServiciosCartas {
    Single<Either<String, Carta>> verUnaCarta(String nombreCarta);

    Either<String, Carta> cartaRandom();

    Either<String, List<ListaSetsCarta>> getAllCardSets();

    Either<String, ListaCartas> verCartasName(String nombre);

    Either<String, ListaCartas> verTodasLasCartas();

    Either<String, ListaCartas> getCardsAtkRace(String nombre, String attack, String race, String sort);
}
