package com.example.examenjoaquinfernandez.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.examenjoaquinfernandez.data.modelo.ComponenteEntity

@Dao
interface ComponentesDao {

    @Query("select * from componentes where nombreEquipo= :nombreEquipo")
    suspend fun getComponentes(nombreEquipo: String): List<ComponenteEntity>
}