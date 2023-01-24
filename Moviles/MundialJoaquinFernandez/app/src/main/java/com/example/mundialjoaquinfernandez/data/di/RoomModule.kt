package com.example.mundialjoaquinfernandez.data.di

import android.content.Context
import androidx.room.Room
import com.example.mundialjoaquinfernandez.data.EquiposDatabase
import com.example.mundialjoaquinfernandez.data.const.Constantes
import com.example.mundialjoaquinfernandez.data.dao.EquiposDao
import com.example.mundialjoaquinfernandez.data.dao.JugadoresDao
import com.example.mundialjoaquinfernandez.data.dao.PartidoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Named(Constantes.EQUIPOS_DATABASE)
    fun getAssetsBD() = Constantes.DATABASE_FILE

    @Provides
    @Singleton
    fun providerDataBase(
        @ApplicationContext t: Context,
        @Named(Constantes.EQUIPOS_DATABASE) assetBD: String): EquiposDatabase =
        Room.databaseBuilder(t, EquiposDatabase::class.java, Constantes.FUTBOL)
            .createFromAsset(assetBD)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesJugadoresDao(roomDB: EquiposDatabase): JugadoresDao =
        roomDB.jugadoresDao()

    @Provides
    fun providesEquiposDao(roomDB: EquiposDatabase): EquiposDao =
        roomDB.equiposDao()

    @Provides
    fun providesPartidosDao(roomDB: EquiposDatabase): PartidoDao =
        roomDB.partidosDao()

}