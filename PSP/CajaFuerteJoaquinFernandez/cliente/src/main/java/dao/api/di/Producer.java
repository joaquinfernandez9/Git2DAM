package dao.api.di;

import com.google.gson.Gson;
import dao.api.config.Config;
import dao.api.utils.AuthorizationInterceptor;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class Producer {
    @Produces
    @Singleton
    public Retrofit getRetrofit(Config config, AuthorizationInterceptor authorizationInterceptor, Gson gson) {
        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(1, 1, TimeUnit.SECONDS))
                .addInterceptor(authorizationInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(config.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();

    }
}
