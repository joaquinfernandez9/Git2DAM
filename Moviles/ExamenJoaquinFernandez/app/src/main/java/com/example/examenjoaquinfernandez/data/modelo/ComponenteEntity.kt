package com.example.examenjoaquinfernandez.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.examenjoaquinfernandez.domain.model.Equipo

@Entity(tableName = "componentes",
foreignKeys = [
    ForeignKey(entity = EquipoEntity::class,
    parentColumns = ["nombreEquipo"],
    childColumns = ["nombreEquipo"])
])
data class ComponenteEntity (
    @PrimaryKey(autoGenerate = false)
    val nombre: String,
    @ColumnInfo(name = "Tipo")
    val tipo: String,


    val nombreEquipo: String
        )