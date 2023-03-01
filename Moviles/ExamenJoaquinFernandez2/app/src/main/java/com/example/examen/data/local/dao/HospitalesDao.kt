package com.example.examen.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.examen.data.model.HospitalEntity
import com.example.examen.domain.model.Hospital

@Dao
interface HospitalesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<HospitalEntity>)

    @Delete
    suspend fun deleteAll(movie: List<HospitalEntity>)

}