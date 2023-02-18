package com.example.mundialjoaquinfernandez.data.dao

import androidx.room.*
import com.example.mundialjoaquinfernandez.data.const.Constantes
import com.example.mundialjoaquinfernandez.data.model.EquipoConJugadores
import com.example.mundialjoaquinfernandez.data.model.EquipoEntity

@Dao
interface EquiposDao {

    //get all
    @Query(Constantes.GET_ALL_EQUIPOS)
    suspend fun getAllEquipos(): List<EquipoEntity>

    //get by name
    @Query(Constantes.GET_EQUIPO_BY_NAME)
    suspend fun getEquipoByName(nombre: String): EquipoEntity

}