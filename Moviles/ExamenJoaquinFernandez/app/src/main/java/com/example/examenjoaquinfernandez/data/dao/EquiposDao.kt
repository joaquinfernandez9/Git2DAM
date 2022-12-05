package com.example.examenjoaquinfernandez.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examenjoaquinfernandez.data.modelo.EquipoEntity

@Dao
interface EquiposDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(equipo: EquipoEntity)

    @Query("delete from equipos where nombreEquipo=:nombre")
    suspend fun delete(nombre: String)

    @Query("select * from equipos order by puestoFinal desc")
    suspend fun getAll(): List<EquipoEntity>

    @Query("select * from equipos where nombreEquipo=:nombre")
    suspend fun getNombre(nombre: String): EquipoEntity

}