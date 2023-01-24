package com.example.mundialjoaquinfernandez.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mundialjoaquinfernandez.data.const.Constantes
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = Constantes.JUGADORES,
    foreignKeys = [
        ForeignKey(
            entity = EquipoEntity::class,
            parentColumns = [Constantes.NOMBRE],
            childColumns = [Constantes.EQUIPO],
        )
    ],
    indices = [
        androidx.room.Index(value = [Constantes.EQUIPO])
    ]
)
data class JugadorEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = Constantes.NOMBRE)
    val nombreJugador: String,
    @ColumnInfo(name = Constantes.DORSAL)
    val dorsal: Int,
    @ColumnInfo(name = Constantes.POSICION)
    val posicion: String,
    @ColumnInfo(name = Constantes.EQUIPO)
    var equipo: String,
) {



}