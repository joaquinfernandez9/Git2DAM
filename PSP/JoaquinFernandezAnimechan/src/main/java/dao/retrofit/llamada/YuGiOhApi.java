package dao.retrofit.llamada;

import dao.retrofit.cards.CardSetsItem;
import dao.retrofit.cards.CardsList;
import dao.retrofit.cards.DataItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.ArrayList;
import java.util.List;

public interface YuGiOhApi {

    @GET("cardinfo.php")
    Call<CardsList> getCardsInfo(@Query("fname") String name);

    @GET("cardinfo.php")
    Call<CardsList> getTodas();

    @GET("cardinfo.php")
    Call<DataItem> getCardName(@Query("name") String name);

    @GET("randomcard.php")
    Call<DataItem> getRandomCard();

    @GET("cardsets.php")
    Call<List<CardSetsItem>> getAllSets();

    @GET("cardinfo.php")
    Call<CardsList> getCardsAtkRace(@Query("fname") String name, @Query("atk") String atk,
                                   @Query("race") String race, @Query("sort") String sort);





}
