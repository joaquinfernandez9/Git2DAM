package com.example.examen.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.examen.data.model.PacienteEntity

@Dao
interface PacientesDao {

    @Query("SELECT * FROM pacientes")
    suspend fun getAll(): List<PacienteEntity>

    @Query("SELECT * from pacientes where id = :id")
    suspend fun getPacientes(id: String): PacienteEntity


    @Insert
    suspend fun insertAll(movies: List<PacienteEntity>)


    @Delete
    suspend fun deleteAll(movie: List<PacienteEntity>)
}