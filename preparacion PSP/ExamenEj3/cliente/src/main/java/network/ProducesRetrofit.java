package network;

import com.google.gson.Gson;
import domain.model.CacheAuth;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit.InformesApi;
import retrofit.LoginApi;
import retrofit.RatonesApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Retrofit getRetrofit(Gson gson, CacheAuth cacheAuthorization) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient clientOK = new OkHttpClient.Builder()
                .readTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .callTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectionPool(new ConnectionPool(1, 1, TimeUnit.SECONDS))
                .addInterceptor(new AuthInterceptor(cacheAuthorization))
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();


        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/servidor-1.0-SNAPSHOT/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();
    }


    @Produces
    @Named("loginApi")
    public LoginApi getLoginApi(Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }

    @Produces
    @Named("ratonesApi")
    public RatonesApi getRatonesApi(Retrofit retrofit) {
        return retrofit.create(RatonesApi.class);
    }

    @Produces
    @Named("informesApi")
    public InformesApi getInformesApi(Retrofit retrofit) {
        return retrofit.create(InformesApi.class);
    }

}
