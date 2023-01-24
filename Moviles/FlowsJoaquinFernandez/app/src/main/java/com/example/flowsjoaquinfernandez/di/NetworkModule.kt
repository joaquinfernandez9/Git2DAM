package com.example.flowsjoaquinfernandez.di

import com.example.flows.BuildConfig
import com.example.flowsjoaquinfernandez.Config
import com.example.flowsjoaquinfernandez.network.AuthInterceptor
import com.example.flowsjoaquinfernandez.network.services.MovieService
import com.example.flowsjoaquinfernandez.network.services.SeriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val baseLink = Config.BASE_URL
    private const val baseLinkImg = Config.IMAGE_URL

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(BuildConfig.API_KEY)) // <- 6b4225ea1fe3f32bb492fece101cdff4
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseLink)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java);
    }

    @Provides
    fun provideSeriesService(retrofit: Retrofit): SeriesService {
        return retrofit.create(SeriesService::class.java);
    }

}