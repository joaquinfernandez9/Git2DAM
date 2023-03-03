package com.example.examendiputadosjoaquinfernandez.data.local.dao

import androidx.room.*
import com.example.examendiputadosjoaquinfernandez.data.model.DiputadoEntity
import com.example.examendiputadosjoaquinfernandez.data.model.PartidoEntity
import com.example.examendiputadosjoaquinfernandez.domain.model.Diputado
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido

@Dao
interface PartidoDao {

    //get all
    @Query("SELECT * FROM diputados")
    suspend fun getAll(): List<DiputadoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(partidos: List<DiputadoEntity>)

    @Delete
    suspend fun deleteAll(partidos: List<DiputadoEntity>)
}