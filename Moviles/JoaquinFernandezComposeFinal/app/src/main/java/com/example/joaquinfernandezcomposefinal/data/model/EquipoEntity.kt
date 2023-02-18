package com.example.mundialjoaquinfernandez.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.mundialjoaquinfernandez.data.const.Constantes


@Entity(tableName = Constantes.EQUIPO)
data class EquipoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = Constantes.NOMBRE)
    val nombreEquipo: String,
)