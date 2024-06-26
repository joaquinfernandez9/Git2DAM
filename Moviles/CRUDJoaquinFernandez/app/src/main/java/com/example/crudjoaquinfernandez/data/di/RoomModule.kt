package com.example.crudjoaquinfernandez.data.di

import android.content.Context
import androidx.room.Room
import com.example.crudjoaquinfernandez.data.ConstData
import com.example.crudjoaquinfernandez.data.dao.HeadsetDao
import com.example.crudjoaquinfernandez.data.HeadsetRoomDataBase
import com.example.crudjoaquinfernandez.data.dao.StoresDao
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
    @Named(ConstData.headsetDB)
    fun getHeadsetDB() = ConstData.getHeadsetDB

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named(ConstData.headsetDB) dataPath: String
    ): HeadsetRoomDataBase =
        Room.databaseBuilder(context, HeadsetRoomDataBase::class.java, ConstData.DATABASE_NAME)
            .createFromAsset(dataPath).fallbackToDestructiveMigration().build()

    @Provides
    fun providesHeadsetDao(headsetDataBase: HeadsetRoomDataBase):
            HeadsetDao =
        headsetDataBase.headsetDao()

    @Provides
    fun providesStoresDao(headsetDataBase: HeadsetRoomDataBase): StoresDao =
        headsetDataBase.storesDao()
}