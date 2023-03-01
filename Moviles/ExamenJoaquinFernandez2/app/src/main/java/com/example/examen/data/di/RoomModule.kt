package com.example.examen.data.di

import android.content.Context
import androidx.room.Room
import com.example.examen.data.db.HospitalesDB
import com.example.examen.data.local.dao.HospitalesDao
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
    @Singleton
    fun providerDataBase(
        @ApplicationContext t: Context,
    ): HospitalesDB {
        return Room.databaseBuilder(
            t, HospitalesDB::class.java,
            "hospitales"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesPartidosDao(roomDB: HospitalesDB): HospitalesDao =
        roomDB.hospitalDao()

}