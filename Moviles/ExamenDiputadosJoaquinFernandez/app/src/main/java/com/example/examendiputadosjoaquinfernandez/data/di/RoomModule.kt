package com.example.examendiputadosjoaquinfernandez.data.di

import android.content.Context
import androidx.room.Room
import com.example.examendiputadosjoaquinfernandez.data.db.DiputadosDB
import com.example.examendiputadosjoaquinfernandez.data.local.dao.PartidoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providerDataBase(
        @ApplicationContext t: Context,
    ): DiputadosDB {
        return Room.databaseBuilder(
            t, DiputadosDB::class.java,
            "hospitales"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providePartidoDao(db: DiputadosDB): PartidoDao = db.partidoDao()
}