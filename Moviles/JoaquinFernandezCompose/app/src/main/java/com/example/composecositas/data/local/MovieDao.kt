package com.example.composecositas.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composecositas.utils.Constants
import com.example.flowsjoaquinfernandez.data.model.MovieEntity

@Dao
interface MovieDao {

    @Query("select * from movies")
    suspend fun getAll(): List<MovieEntity>

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)


}