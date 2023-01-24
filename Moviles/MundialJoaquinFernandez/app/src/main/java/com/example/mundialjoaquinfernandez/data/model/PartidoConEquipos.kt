package com.example.mundialjoaquinfernandez.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.mundialjoaquinfernandez.data.const.Constantes

data class PartidoConEquipos(
    @Embedded val partido: PartidoEntity,
    @Relation(
        parentColumn = Constantes.ID_PARTIDO,
        entityColumn = Constantes.NOMBRE_EQUIPO,
        associateBy = Junction(PartidoEquipoCrossRef::class)
    )
    val equipos: List<EquipoEntity>,
)