package com.example.examenjoaquinfernandez.data.modelo

import androidx.room.Embedded
import androidx.room.Relation

data class EquipoWithComponente (
    @Embedded val equipo: EquipoEntity,
    @Relation(
        parentColumn = "nombre",
        entityColumn = "nombreEquipo",
    )
    val componentes: List<ComponenteEntity>?,
        )