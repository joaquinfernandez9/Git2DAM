package dao.retrofit;

import com.squareup.moshi.Moshi;
import common.Config;
import dao.retrofit.llamada.YuGiOhApi;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi()
    {
        return new Moshi.Builder().build();
    }

    private final Config config;

    @Inject
    public ProducesRetrofit(Config config) {
        this.config = config;
    }

    @Produces
    @Singleton
    public Retrofit retrofit(Moshi moshi) {
        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 5,
                        java.util.concurrent.TimeUnit.SECONDS))
                .build();

        return new Retrofit.Builder()
                .baseUrl(config.getPath())
                .addConverterFactory(retrofit2.converter.moshi.MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();
    }

    @Produces
    public YuGiOhApi getYugiApi(Retrofit retrofit){
        return retrofit.create(YuGiOhApi.class);
    }

}
