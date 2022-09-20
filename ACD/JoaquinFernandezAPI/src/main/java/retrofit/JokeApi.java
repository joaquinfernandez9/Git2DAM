package retrofit;

import modelo.jokes.ResponseJoke;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JokeApi {


    //enlace de esto / any ? lang=es
    @GET("Any")
    Call<ResponseJoke> getAnyJoke(@Query("lang") String lang);

//    @GET("Any")
//    Call<ResponseJokeSimple> getAnyJokeSimple(@Query("lang") String lang, @Query("type") String type);

    @GET("Programming")
    Call<ResponseJoke> getProgrammingJoke(@Query("lang") String lang);



}
