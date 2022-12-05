package com.example.crudjoaquinfernandez.data

import androidx.room.Database
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import androidx.room.RoomDatabase
import com.example.crudjoaquinfernandez.data.dao.HeadsetDao
import com.example.crudjoaquinfernandez.data.dao.StoresDao
import com.example.crudjoaquinfernandez.data.modelo.ModelEntity
import com.example.crudjoaquinfernandez.data.modelo.StoreEntity


@Database(entities = [HeadsetEntity::class, ModelEntity::class, StoreEntity::class], version = 14, exportSchema = true)
abstract class HeadsetRoomDataBase: RoomDatabase(){
    abstract fun headsetDao(): HeadsetDao
    abstract fun storesDao():StoresDao
}