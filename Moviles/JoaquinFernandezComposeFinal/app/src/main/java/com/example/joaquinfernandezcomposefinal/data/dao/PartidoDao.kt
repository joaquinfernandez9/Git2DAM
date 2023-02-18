package com.example.mundialjoaquinfernandez.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.mundialjoaquinfernandez.data.const.Constantes
import com.example.mundialjoaquinfernandez.data.model.PartidoConEquipos
import com.example.mundialjoaquinfernandez.data.model.PartidoEntity
import com.example.mundialjoaquinfernandez.data.model.PartidoEquipoCrossRef

@Dao
interface PartidoDao {
    //insert partido
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(partido: PartidoEntity) : Long
    //insert en crossref
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCrossRef(partidoConEquipos: PartidoEquipoCrossRef)
    //transaction
    @Transaction
    suspend fun insertPartidoConSelecciones(partido: PartidoConEquipos){
        val partidoCosa = insert(partido.partido)
        val id = partidoCosa.toInt()
        partido.equipos.forEach {
            insertCrossRef(PartidoEquipoCrossRef(id, it.nombreEquipo))
        }
    }

    //get id partido
    @Query(Constantes.GET_ID_PARTIDO)
    suspend fun getPartido(nombreLocal: String, nombreVisitante: String): Long


    //get partido by id
    @Query(Constantes.GET_PARTIDO_BY_ID)
    suspend fun getPartidoById(id: Int): PartidoEntity



}