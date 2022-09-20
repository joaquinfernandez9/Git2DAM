package ui;

import com.squareup.moshi.Moshi;
import dao.retrofit.YuGiOhApi;
import modelo.ResponseApi;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Moshi moshi = new Moshi.Builder().build();

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                .build();

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://db.ygoprodeck.com/api/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(clientOK)
                .build();

        YuGiOhApi yugi = retro.create(YuGiOhApi.class);

        Response<ResponseApi> r = yugi.getCardInfo("Tornado Dragon").execute();

        System.out.println(r.body());

//        ResponseApi<ResponseApi> r2 = yugi.getQuoteFromCharacter("saitama").execute();
//
//        System.out.println(r2.body());


    }
}