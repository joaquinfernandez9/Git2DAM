package com.example.examenjoaquinfernandez.data.di

import android.content.Context
import androidx.room.Room
import com.example.examenjoaquinfernandez.data.EquiposRoomDatabase
import com.example.examenjoaquinfernandez.data.dao.ComponentesDao
import com.example.examenjoaquinfernandez.data.dao.EquiposDao
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
    @Named("equiposDataBase")
    fun getEquiposDB() = "database/equipos.db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("equiposDataBase") datapath: String,
    ): EquiposRoomDatabase =
        Room.databaseBuilder(context, EquiposRoomDatabase::class.java,
        "equipos_database").createFromAsset(datapath)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesEquiposDao(database: EquiposRoomDatabase):
            EquiposDao =
        database.equiposDao()

    @Provides
    fun providesComponentesDao(database: EquiposRoomDatabase):
            ComponentesDao =
        database.componentesDao()

}