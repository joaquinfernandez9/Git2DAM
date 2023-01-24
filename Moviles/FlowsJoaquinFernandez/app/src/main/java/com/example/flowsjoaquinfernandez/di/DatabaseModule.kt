package com.example.flowsjoaquinfernandez.di

import android.content.Context
import androidx.room.Room
import com.example.flowsjoaquinfernandez.data.local.AppDataBase
import com.example.flowsjoaquinfernandez.data.local.MovieDao
import com.example.flowsjoaquinfernandez.data.local.SeriesDao
import com.example.flowsjoaquinfernandez.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext context: Context)
            : AppDataBase {
        return Room.databaseBuilder(
            context, AppDataBase::class.java,
            Constants.TMDB
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDataBase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    fun provideSeriesDao(appDatabase: AppDataBase): SeriesDao {
        return appDatabase.seriesDao()
    }

}