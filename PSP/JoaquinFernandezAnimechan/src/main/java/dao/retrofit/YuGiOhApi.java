package dao.retrofit;

import domain.modelo.cards.DataItem;
import domain.modelo.cards.CardsList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YuGiOhApi {

    @GET("v7/cardinfo.php")
    Call<CardsList> getCardsInfo(@Query("fname") String name);

    @GET("v7/cardinfo.php")
    Call<CardsList> getCardName(@Query("name") String name);

//    @GET("v7/carinfo.php")
//    Call<CardsList> getCardsAtkAtt(@Query("fname") String name, @Query("atk") Integer atk,
//                                   @Query("attribute") String attribute, @Query("sort") String sort);
    @GET("v7/cardinfo.php")
    Call<CardsList> getCardsAtkRace(@Query("fname") String name, @Query("atk") String atk,
                                   @Query("race") String race, @Query("sort") String sort);





}
