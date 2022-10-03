package dao.retrofit.llamada;

import domain.modelo.cards.DataItem;
import domain.modelo.cards.CardsList;
import javafx.scene.control.Cell;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YuGiOhApi {

    @GET("v7/cardinfo.php")
    Call<CardsList> getCardsInfo(@Query("fname") String name);

    @GET("v7/cardinfo.php")
    Call<CardsList> getTodas();

    @GET("v7/cardinfo.php")
    Call<CardsList> getCardName(@Query("name") String name);

    @GET("v7/cardinfo.php")
    Call<CardsList> getCardsAtkRace(@Query("fname") String name, @Query("atk") String atk,
                                   @Query("race") String race, @Query("sort") String sort);





}
