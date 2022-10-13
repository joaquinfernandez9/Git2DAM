package dao.impl;

import dao.DaoCartas;
import dao.retrofit.cards.CardSetsItem;
import dao.retrofit.cards.CardsList;
import dao.retrofit.cards.DataItem;
import dao.retrofit.llamada.YuGiOhApi;
import domain.modelo.*;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Log4j2
public class DaoCartasImpl implements DaoCartas {


    private final YuGiOhApi api;

    @Inject
    public DaoCartasImpl(YuGiOhApi yuGiOhApi) {
        this.api = yuGiOhApi;
    }

    @Override
    public Either<String, Carta> cartaRandom() {
        Either<String, Carta> respuesta = null;
        Response<DataItem> r;

        try {
            r = api.getRandomCard().execute();
            if (r.isSuccessful()) {
                DataItem cartas = r.body();
                if (cartas != null) {
                    //nullpointer
                    Carta cartita;
                    cartita = crearCarta(cartas);
                    respuesta = Either.right(cartita);
                } else {
                    respuesta = Either.left("Error");
                }
            } else {
                respuesta = Either.left("Error");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Single<Either<String, Carta>> verUnaCarta(String nombre) {
//        Single<String, Carta> respuesta;
//        Response<CardsList> r;
//        try {
//            r = api.getCardName(nombre).execute();
//            if (r.isSuccessful()) {
//                CardsList cartas = r.body();
//                if (cartas != null) {
//                    Carta cartita;
//                    cartita = crearCarta(cartas.getData().get(0));
//                    respuesta = Either.right(cartita);
//                } else {
//                    respuesta = Either.left(nombre);
//                }
//            } else {
//                respuesta = Either.left(r.message());
//            }
//        } catch (IOException e) {
//            respuesta = Either.left(e.getMessage());
//        }
//        return respuesta;
        return api.getCardName(nombre)
                .map(card -> {
                    Carta cartita;
                    cartita = crearCarta(card.getData().get(0));
                    //el mapper devuelve nulo
                    return Either.right(cartita)
                            .mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> Either.left("Error de comunicacion"));
    }

//    @Override
//    public Single<Carta> verUnaCarta(String nombre) {
//        return api.getCardName(nombre)
//                .map(card -> {
//                    Carta cartita;
//                    cartita = crearCarta(card.getData().get(0));
//                    el mapper devuelve nulo
//                    return cartita;
//                })
//                .subscribeOn(Schedulers.io());
//    }

    @Override
    public Either<String, List<ListaSetsCarta>> getAllCardSets() {

        Response<List<CardSetsItem>> r;
        Either<String, List<ListaSetsCarta>> respuesta = null;
        try {
            r = api.getAllSets().execute();
            if (r.isSuccessful()) {
                List<CardSetsItem> setsItem = r.body();
                if (setsItem != null) {
                    List<ListaSetsCarta> listaSetsCarta;
                    listaSetsCarta = setsItem.stream().map(cardSetsItem ->
                            new ListaSetsCarta(cardSetsItem.getSet_code(),
                                    cardSetsItem.getSet_name()
                            )).toList();
                    respuesta = Either.right(listaSetsCarta);
                } else {
                    respuesta = Either.left("Error");
                }
            } else {
                respuesta = Either.left("Error");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
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
                    respuesta = getListaCartitas(cartas);
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
        Response<CardsList> r;
        Either<String, ListaCartas> respuesta;
        try {
            r = api.getTodas().execute();
            respuesta = rSuccessful(r);
        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Either<String, ListaCartas> getCardsAtkRace(String nombre, String atk,
                                                       String race, String sort) {
        Response<CardsList> r;
        Either<String, ListaCartas> respuesta;
        try {
            r = api.getCardsAtkRace(nombre, atk, race, sort).execute();
            respuesta = rSuccessful(r);
        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    @NotNull
    private Either<String, ListaCartas> rSuccessful(Response<CardsList> r) {
        Either<String, ListaCartas> respuesta;
        if (r.isSuccessful()) {
            CardsList cartas = r.body();
            if (r.body() != null && cartas != null) {
                respuesta = getListaCartitas(cartas);
            } else {
                respuesta = Either.left(r.message());
            }
        } else {
            respuesta = Either.left(r.message());
        }
        return respuesta;
    }

    @NotNull
    private Carta crearCarta(DataItem data) {
        return new Carta(data.getName(), data.getId(), data.getLevel(),
                data.getAtk(), data.getDef(), data.getType(), data.getRace(),
                data.getAttribute(), data.getDesc(), data.getArchetype(),
                data.getCard_prices().stream().map(cardPricesItem ->
                        new ListaPreciosCarta(
                                data.getCard_prices().stream().map(cardPricesItem1 ->
                                        cardPricesItem.getCoolstuffinc_price()).toString(),
                                data.getCard_prices().stream().map(cardPricesItem1 ->
                                        cardPricesItem.getTcgplayer_price()).toString(),
                                data.getCard_prices().stream().map(cardPricesItem1 ->
                                        cardPricesItem.getAmazon_price()).toString(),
                                data.getCard_prices().stream().map(cardPricesItem1 ->
                                        cardPricesItem.getEbay_price()).toString(),
                                data.getCard_prices().stream().map(cardPricesItem1 ->
                                        cardPricesItem.getCardmarket_price()).toString()
                        )).toList(),
                data.getCard_images().stream().map(cardImagesItem ->
                        new ListaImgCarta(
                                data.getCard_images().stream().map(cardImagesItem1 ->
                                        cardImagesItem.getImage_url()).toString()
                        )).toList()
        );
    }

    @NotNull
    private Either<String, ListaCartas> getListaCartitas(CardsList cartas) {
        Either<String, ListaCartas> respuesta;
        ListaCartas cartitas;
        cartitas = new ListaCartas(cartas.getData().stream().map(this::crearCarta).toList());
        respuesta = Either.right(cartitas);

        return respuesta;
    }

}
