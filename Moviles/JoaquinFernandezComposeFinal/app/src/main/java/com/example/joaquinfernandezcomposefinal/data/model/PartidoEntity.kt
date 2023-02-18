package com.example.mundialjoaquinfernandez.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mundialjoaquinfernandez.data.const.Constantes

@Entity(tableName = Constantes.PARTIDO,)
data class PartidoEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constantes.CAPS_ID)
    val id: Int,
    @ColumnInfo(name = Constantes.GOLES_LOCAL)
    val golLocal: Int,
    @ColumnInfo(name = Constantes.GOLES_VISITANTE)
    val golVisitante: Int,
    @ColumnInfo(name = Constantes.FECHA)
    val fecha: String,
        )   {
    constructor(golesLocal: Int, golesVisitante: Int, fecha: String) : this(0, golesLocal, golesVisitante, fecha)
}
