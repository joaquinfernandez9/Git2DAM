package dao;

import com.squareup.moshi.Moshi;
import dao.retrofit.YuGiOhApi;
import domain.modelo.cards.CardsList;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

public class DaoCartas {


   Moshi moshi = new Moshi.Builder().build();
   OkHttpClient clientOK = new OkHttpClient.Builder()
           .connectionPool(new okhttp3.ConnectionPool(1, 5, java.util.concurrent.TimeUnit.SECONDS))
           .build();
   Retrofit retro = new Retrofit.Builder()
           .baseUrl("https://db.ygoprodeck.com/api/")
           .addConverterFactory(retrofit2.converter.moshi.MoshiConverterFactory.create(moshi))
           .client(clientOK)
           .build();

   YuGiOhApi yuGiOhApi = retro.create(YuGiOhApi.class);



    public CardsList verUnaCarta(String nombre) throws IOException {
        Response<CardsList> carta = yuGiOhApi.getCardName(nombre).execute();
        assert carta.body() != null;
        return carta.body();
    }


    public CardsList verCartasConNombre(String nombre) throws IOException {

        Response<CardsList> response = yuGiOhApi.getCardsInfo(nombre).execute();

        assert response.body() != null;
        return response.body();

    }


    //show all cards
    public CardsList verTodasLasCartas() throws IOException {

        Response<CardsList> response = yuGiOhApi.getCardsInfo("").execute();

        assert response.body() != null;
        return response.body();

    }

    public CardsList getCardsAtkRace(String nombre, String atk, String race, String sort) throws IOException{
        Response<CardsList> lista = yuGiOhApi.getCardsAtkRace(nombre, atk, race, sort).execute();
        assert lista.body() != null;
        return lista.body();
    }


}
