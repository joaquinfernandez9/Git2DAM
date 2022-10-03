package dao.retrofit;

import com.squareup.moshi.Moshi;
import dao.retrofit.llamada.YuGiOhApi;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi()
    {
        return new Moshi.Builder().build();
    }

    @Produces
    @Singleton
    public Retrofit retrofit(Moshi moshi) {

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 5,
                        java.util.concurrent.TimeUnit.SECONDS))
                .build();


        return new Retrofit.Builder()
                .baseUrl("https://db.ygoprodeck.com/api/")
                .addConverterFactory(retrofit2.converter.moshi.MoshiConverterFactory.create(moshi))
                .client(clientOK)
                .build();
    }

    @Produces
    public YuGiOhApi getYugiApi(Retrofit retrofit){
        return retrofit.create(YuGiOhApi.class);
    }

}
