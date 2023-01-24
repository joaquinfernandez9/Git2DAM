package com.example.mundialjoaquinfernandez.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.mundialjoaquinfernandez.data.const.Constantes

@Entity(primaryKeys = [Constantes.ID_PARTIDO, Constantes.NOMBRE_EQUIPO])
data class PartidoEquipoCrossRef (
    @ColumnInfo(name = Constantes.ID_PARTIDO)
    val idPartido : Int,
    @ColumnInfo(name = Constantes.NOMBRE_EQUIPO)
    val nombreEquipo: String
        )