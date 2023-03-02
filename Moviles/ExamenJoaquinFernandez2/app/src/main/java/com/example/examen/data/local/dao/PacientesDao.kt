package com.example.examen.data.local.dao

import androidx.room.*
import com.example.examen.data.model.PacienteEntity

@Dao
interface PacientesDao {

    @Query("SELECT * FROM pacientes")
    suspend fun getAll(): List<PacienteEntity>

    @Query("SELECT * from pacientes where id = :id")
    suspend fun getPacientes(id: String): PacienteEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<PacienteEntity>)


    @Delete
    suspend fun deleteAll(movie: List<PacienteEntity>)
}