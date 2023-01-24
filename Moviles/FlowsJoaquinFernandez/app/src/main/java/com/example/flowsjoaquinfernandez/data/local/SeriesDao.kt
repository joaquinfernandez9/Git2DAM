package com.example.flowsjoaquinfernandez.data.local

import androidx.room.*
import com.example.flowsjoaquinfernandez.data.model.MovieEntity
import com.example.flowsjoaquinfernandez.data.model.SerieEntity
import com.example.flowsjoaquinfernandez.utils.Constants

@Dao
interface SeriesDao {
    @Query(Constants.GET_ALL_FROM_SERIE)
    suspend fun getAll(): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<SerieEntity>)

    @Delete
    suspend  fun delete(movie: SerieEntity)

    @Delete
    suspend fun deleteAll(movie: List<SerieEntity>)
}