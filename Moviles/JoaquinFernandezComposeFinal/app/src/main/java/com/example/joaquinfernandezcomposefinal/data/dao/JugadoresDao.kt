package com.example.mundialjoaquinfernandez.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mundialjoaquinfernandez.data.const.Constantes
import com.example.mundialjoaquinfernandez.data.model.JugadorEntity

@Dao
interface JugadoresDao {

    @Query(Constantes.GET_ALL_JUGADORES_DE_EQUIPO)
    suspend fun getAll(nombreEquipo: String): List<JugadorEntity>

    @Query(Constantes.DELETE_JUGADOR)
    suspend fun delete(nombre: String)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(jugador: JugadorEntity)


}