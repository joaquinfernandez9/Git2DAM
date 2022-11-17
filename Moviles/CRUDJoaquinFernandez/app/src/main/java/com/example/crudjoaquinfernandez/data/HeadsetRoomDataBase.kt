package com.example.crudjoaquinfernandez.data

import androidx.room.Database
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import androidx.room.RoomDatabase
import com.example.crudjoaquinfernandez.data.dao.HeadsetDao
import com.example.crudjoaquinfernandez.data.modelo.ModelEntity


@Database(entities = [HeadsetEntity::class, ModelEntity::class], version = 13, exportSchema = true)
abstract class HeadsetRoomDataBase: RoomDatabase(){
    abstract fun headsetDao(): HeadsetDao

}