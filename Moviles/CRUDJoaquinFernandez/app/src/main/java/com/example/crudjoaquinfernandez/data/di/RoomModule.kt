package com.example.crudjoaquinfernandez.data.di

import android.content.Context
import androidx.room.Room
import com.example.crudjoaquinfernandez.data.ConstData
import com.example.crudjoaquinfernandez.data.dao.HeadsetDao
import com.example.crudjoaquinfernandez.data.HeadsetRoomDataBase
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
    @Named("headsetDB")
    fun getHeadsetDB() = "database/cascos.db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("headsetDB") dataPath: String
    ): HeadsetRoomDataBase =
        Room.databaseBuilder(context, HeadsetRoomDataBase::class.java, ConstData.databaseName)
            .createFromAsset(dataPath).fallbackToDestructiveMigration().build()

    @Provides
    fun providesHeadsetDao(headsetDataBase: HeadsetRoomDataBase): HeadsetDao =
        headsetDataBase.headsetDao()
}