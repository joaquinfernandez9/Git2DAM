package dao;

import domain.modelo.Carta;
import domain.modelo.ListaCartas;
import domain.modelo.ListaSetsCarta;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface DaoCartas {
    Single<Either<String, Carta>> verUnaCarta(String nombre);

    Either<String, Carta> cartaRandom();

    Either<String, List<ListaSetsCarta>> getAllCardSets();

    Either<String, ListaCartas> verCartasConNombre(String nombre);

    Either<String, ListaCartas> verTodasLasCartas();

    Either<String, ListaCartas> getCardsAtkRace(String nombre, String atk, String race, String sort);
}
