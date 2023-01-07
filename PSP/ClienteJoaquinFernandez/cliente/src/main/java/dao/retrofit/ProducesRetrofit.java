package dao.retrofit;

import com.google.gson.Gson;
import dao.Common;
import dao.retrofit.config.ConfigApi;
import dao.retrofit.llamadas.LoginApi;
import dao.retrofit.llamadas.NewspaperApi;
import dao.retrofit.llamadas.QuerysApi;
import dao.retrofit.llamadas.ReadersApi;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Retrofit getRetrofit(ConfigApi configApi, Gson gson) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient clientOK;

        clientOK = new OkHttpClient.Builder()
                .readTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .callTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectTimeout(Duration.of(10, ChronoUnit.MINUTES))
                //.addInterceptor(new AuthorizationInterceptor(cache))
                .connectionPool(new ConnectionPool(1, 1, TimeUnit.SECONDS))
                // necesario para la sesion
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        return new Retrofit.Builder()
                .baseUrl(configApi.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();
    }

    @Produces
    public QuerysApi getQuerysApi(Retrofit retrofit) {
        return retrofit.create(QuerysApi.class);
    }

    @Produces
    public NewspaperApi getNewspaperApi(Retrofit retrofit) {
        return retrofit.create(NewspaperApi.class);
    }

    @Produces
    public ReadersApi getReadersApi(Retrofit retrofit) {
        return retrofit.create(ReadersApi.class);
    }

    @Produces
    public LoginApi getLoginApi(Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }

}
