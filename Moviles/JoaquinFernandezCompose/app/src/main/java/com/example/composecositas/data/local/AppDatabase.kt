package com.example.composecositas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flowsjoaquinfernandez.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}