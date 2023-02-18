package com.example.composecositas.data.di

import android.content.Context
import androidx.room.Room
import com.example.composecositas.data.local.AppDatabase
import com.example.composecositas.data.local.MovieDao
import com.example.composecositas.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Named("moviesDB")
    fun getAssetsBD() = "database/movies.db"

    @Provides
    @Singleton
    fun provideAppDB(
        @ApplicationContext context: Context,
        @Named("moviesDB") assetDB: String
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.MOVIES)
            .createFromAsset(assetDB)
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }
}