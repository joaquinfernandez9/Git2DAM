package dao.impl;

import dao.DaoCartas;
import dao.retrofit.llamada.YuGiOhApi;
import domain.modelo.cards.CardsList;
import domain.modelo.cards.DataItem;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import java.io.IOException;


@Log4j2
public class DaoCartasImpl implements DaoCartas {


    private final YuGiOhApi api;

    @Inject
    public DaoCartasImpl(YuGiOhApi yuGiOhApi) {
        this.api = yuGiOhApi;
    }

    @Override
    public Either<String, DataItem> verUnaCarta(String nombre) {
        Either<String, DataItem> respuesta;
        Response<CardsList> r;
        try {
            r = api.getCardName(nombre).execute();

            if (r.isSuccessful()) {
                if (r.body() != null) {
                    respuesta = Either.right(r.body().getData().get(0));
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
    public Either<String, CardsList> verCartasConNombre(String nombre) {
        Response<CardsList> r;
        Either<String, CardsList> respuesta;
        try {
            r = api.getCardsInfo(nombre).execute();
            if (r.isSuccessful()) {
                if (r.body() != null) {
                    respuesta = Either.right(r.body());
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
    public Either<String, CardsList> verTodasLasCartas() {
        Response<CardsList> r;
        Either<String, CardsList> respuesta;
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
    public Either<String, CardsList> getCardsAtkRace(String nombre, String atk, String race, String sort) {
        Response<CardsList> r;
        Either<String, CardsList> respuesta;
        try {
            r = api.getCardsAtkRace(nombre, atk, race, sort).execute();
            if (r.isSuccessful()){
                if (r.body()!=null){
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
