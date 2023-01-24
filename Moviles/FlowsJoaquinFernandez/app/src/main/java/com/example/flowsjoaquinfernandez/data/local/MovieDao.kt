package com.example.flowsjoaquinfernandez.data.local

import androidx.room.*
import com.example.flowsjoaquinfernandez.data.model.MovieEntity
import com.example.flowsjoaquinfernandez.utils.Constants

@Dao
interface MovieDao {
    @Query(Constants.GET_ALL_MOVIES_POP)
    suspend fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Delete
    suspend fun deleteAll(movie: List<MovieEntity>)
}