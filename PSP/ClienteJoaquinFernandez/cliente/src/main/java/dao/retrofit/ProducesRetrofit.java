package dao.retrofit;

import com.google.gson.Gson;
import dao.Common;
import dao.retrofit.config.ConfigApi;
import dao.retrofit.llamadas.NewspaperApi;
import dao.retrofit.llamadas.QuerysApi;
import dao.retrofit.llamadas.ReadersApi;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Retrofit getRetrofit(ConfigApi configApi, Gson gson) {

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                .addInterceptor(chain -> {
                            Request original = chain.request();

                            Request request = original.newBuilder()
                                    .header(Common.PROTOCOL_REQUEST, Common.HTTP_2_0)
                                    .header(Common.ACCEPT, Common.APPLICATION_JSON)
                                    .method(original.method(), original.body())
                                    .build();

                            return chain.proceed(request);
                        }
                ).build();

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

}
