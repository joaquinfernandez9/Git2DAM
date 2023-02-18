package com.example.mundialjoaquinfernandez.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mundialjoaquinfernandez.data.const.Constantes

data class EquipoConJugadores (
    @Embedded val equipo: EquipoEntity,
    @Relation(
        parentColumn = Constantes.NOMBRE,
        entityColumn = Constantes.EQUIPO
    )
    val jugadores: List<JugadorEntity>
        )