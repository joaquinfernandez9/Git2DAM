package com.example.flowsjoaquinfernandez.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flowsjoaquinfernandez.data.model.MovieEntity
import com.example.flowsjoaquinfernandez.data.model.SerieEntity

@Database(entities = [MovieEntity::class,SerieEntity::class], version = 5, exportSchema = false)
abstract class AppDataBase: RoomDatabase(){
    abstract fun movieDao(): MovieDao
    abstract fun seriesDao(): SeriesDao
}