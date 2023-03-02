package com.example.examen.data.di

import android.content.Context
import androidx.room.Room
import com.example.examen.data.db.HospitalesDB
import com.example.examen.data.local.dao.HospitalesDao
import com.example.examen.data.local.dao.PacientesDao
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
    ): HospitalesDB {
        return Room.databaseBuilder(
            t, HospitalesDB::class.java,
            "hospitales"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesHospitalesDao(roomDB: HospitalesDB): HospitalesDao =
        roomDB.hospitalDao()

    @Provides
    fun providesPacientesDao(roomDB: HospitalesDB): PacientesDao =
        roomDB.pacientesDao()

}