package dao;

import com.squareup.moshi.Moshi;
import dao.retrofit.ProducesRetrofit;
import dao.retrofit.YuGiOhApi;
import domain.modelo.cards.CardPricesItem;
import domain.modelo.cards.CardsList;
import domain.modelo.cards.DataItem;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.io.IOException;
import java.util.List;


@Log4j2
public class DaoCartas {


    private YuGiOhApi api;

    @Inject
    public DaoCartas(YuGiOhApi yuGiOhApi) {
        this.api = yuGiOhApi;
    }

    public Either<String, DataItem> verUnaCarta(String nombre){
        Either<String, DataItem> respuesta;
        Response<CardsList> r;
        try {
            r = api.getCardName(nombre).execute();
            if (r.body()== null){
                respuesta=  Either.left(nombre);
            } else {
                respuesta= Either.right(r.body().getData().get(0));
            }
        } catch (IOException e){
            respuesta= Either.left(e.getMessage());
        }
        return respuesta;
    }

    public Either<String, List<CardPricesItem>> verValorCarta(String nombre){
            DataItem carta = verUnaCarta(nombre).get();
            return Either.right(carta.getCard_prices());
    }




    public Either<String,CardsList> verCartasConNombre(String nombre){
        Response<CardsList> r;
        try {
            r = api.getCardsInfo(nombre).execute();
            assert r.body()!=null;
            return Either.right(r.body());
        }catch (IOException e) {
            return Either.left(e.getMessage());
        }

    }
    public Either<String,CardsList> verTodasLasCartas(){
        Response<CardsList> r;

        try {
            r= api.getCardsInfo("").execute();
            assert r.body() != null;
            return Either.right(r.body());
        } catch (IOException e){
            return Either.left(e.getMessage());
        }
    }

    public Either<String, CardsList> getCardsAtkRace
            (String nombre, String atk, String race, String sort){

        Response<CardsList> lista;
        try {
            lista = api
                    .getCardsAtkRace(nombre, atk, race, sort).execute();
            //cabiar el asser por que queda feo en todos lados pero deberia funcionar
            assert lista.body() != null;
            return Either.right(lista.body());
        } catch (IOException e){
            return Either.left(e.getMessage());
        }
    }


}
