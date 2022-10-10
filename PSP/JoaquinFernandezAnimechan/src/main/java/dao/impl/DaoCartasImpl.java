package dao.impl;

import dao.DaoCartas;
import dao.retrofit.cards.CardsList;
import dao.retrofit.cards.DataItem;
import dao.retrofit.llamada.YuGiOhApi;
import domain.modelo.Carta;
import domain.modelo.ListaCartas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import java.io.IOException;
import java.util.stream.Collectors;


@Log4j2
public class DaoCartasImpl implements DaoCartas {


    private final YuGiOhApi api;

    @Inject
    public DaoCartasImpl(YuGiOhApi yuGiOhApi) {
        this.api = yuGiOhApi;
    }

    @Override
    public Either<String, Carta> verUnaCarta(String nombre) {
        Either<String, Carta> respuesta = null;
        Response<CardsList> r;
        try {
            r = api.getCardName(nombre).execute();

            if (r.isSuccessful()) {
                CardsList cartas = r.body();
                if (cartas != null) {
                    DataItem data = cartas.getData().get(0);
                    Carta cartita;
                    cartita = new Carta(data.getName(), data.getId(), data.getLevel(),
                            data.getAtk(), data.getDef(), data.getType(), data.getRace(),
                            data.getAttribute(), data.getDesc(), data.getArchetype(),
                            data.getCard_sets(), data.getCard_prices(), data.getCard_images()
                    );
                    respuesta = Either.right(cartita);
                } else {
                    respuesta = Either.left(nombre);
                }
            } else {
                respuesta = Either.left(r.message());
            }
        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Either<String, ListaCartas> verCartasConNombre(String nombre) {
        Response<CardsList> r;
        Either<String, ListaCartas> respuesta;
        try {
            r = api.getCardsInfo(nombre).execute();
            if (r.isSuccessful()) {
                CardsList cartas = r.body();
                if (cartas != null) {
                    ListaCartas cartitas;
                    cartitas = new ListaCartas(cartas.getData().stream().map(carta ->
                            new Carta(carta.getName(), carta.getId(), carta.getLevel(),
                                    carta.getAtk(), carta.getDef(), carta.getType(), carta.getRace(),
                                    carta.getAttribute(), carta.getDesc(), carta.getArchetype(),
                                    carta.getCard_sets(), carta.getCard_prices(), carta.getCard_images())).toList());
                    respuesta = Either.right(cartitas);
                } else {
                    respuesta = Either.left(nombre);
                }
            } else {
                respuesta = Either.left(r.message());
            }

        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Either<String, ListaCartas> verTodasLasCartas() {
        Response<ListaCartas> r;
        Either<String, ListaCartas> respuesta;
        try {
            r = api.getTodas().execute();
            if (r.isSuccessful()) {
                if (r.body() != null) {
                    respuesta = Either.right(r.body());
                } else {
                    respuesta = Either.left(r.message());
                }
            } else {
                respuesta = Either.left(r.message());
            }
        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Either<String, ListaCartas> getCardsAtkRace(String nombre, String atk, String race, String sort) {
        Response<ListaCartas> r;
        Either<String, ListaCartas> respuesta;
        try {
            r = api.getCardsAtkRace(nombre, atk, race, sort).execute();
            if (r.isSuccessful()) {
                if (r.body() != null) {
                    respuesta = Either.right(r.body());
                } else {
                    respuesta = Either.left(r.message());
                }
            } else {
                respuesta = Either.left(r.message());
            }
        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

}
