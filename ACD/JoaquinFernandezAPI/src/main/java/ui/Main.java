package ui;

import com.squareup.moshi.Moshi;
import modelo.jokes.ResponseJoke;
import okhttp3.OkHttpClient;
import modelo.jokes.ResponseJoke;
import modelo.RespuestaAPI;
import retrofit.JokeApi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        //bc2576d758d70ff8cb4844883583233fddbf5339
        //https://comicvine.gamespot.com/api
        Moshi moshi = new Moshi.Builder().build();

//        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
//                (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString())).registerTypeAdapter(LocalDateTime.class,
//                (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.toString())
//        ).create();


        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                //.protocols(java.util.Arrays.asList(Protocol.HTTP_2,Protocol.H2_PRIOR_KNOWLEDGE))
                .build();
//
//
//mejor que no
//        System.out.println(clientOK
//                .newCall(
//                        new Request.Builder()
//                                .url("https://v2.jokeapi.dev/joke/Any?lang=es").build())
//                .execute().body().string());

//    enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                System.out.println("Error");
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                System.out.println(response.body().string());
//            }
//        });

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://v2.jokeapi.dev/joke/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientOK)
                .build();

        JokeApi jokes =  retro.create(JokeApi.class);

        Response<ResponseJoke> r = jokes.getAnyJoke("es").execute();


        System.out.println(r.body());

//                .enqueue(new Callback<ResponseJoke>() {
//            @Override
//            public void onResponse(Call<ResponseJoke> call, Response<ResponseJoke> response) {
//                System.out.println(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseJoke> call, Throwable throwable) {
//                System.out.println(throwable);
//            }
//        });

//        if (r.isSuccessful())
//        {
//            System.out.println(r.body());
//        }

        // System.out.println(clientOK.connectionPool().connectionCount());

    }


}
