package com.example.examenjoaquinfernandez.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "equipos",
    indices = [Index(value = ["puestoFinal"], unique = true)])
data class EquipoEntity (
    @PrimaryKey(autoGenerate = false)
    val nombreEquipo: String,
    @ColumnInfo(name = "nacionalidad")
    val nacionalidad: String,
    @ColumnInfo(name = "puestoFinal")
    val puestoFinal: Int,
        )