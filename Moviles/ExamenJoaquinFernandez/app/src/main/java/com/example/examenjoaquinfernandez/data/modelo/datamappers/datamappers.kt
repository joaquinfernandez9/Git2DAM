package com.example.examenjoaquinfernandez.data.modelo.datamappers

import com.example.examenjoaquinfernandez.data.modelo.ComponenteEntity
import com.example.examenjoaquinfernandez.data.modelo.EquipoEntity
import com.example.examenjoaquinfernandez.domain.model.Componente
import com.example.examenjoaquinfernandez.domain.model.Equipo

fun ComponenteEntity.toComponente(): Componente {
    return Componente(
        this.nombre, this.tipo
    )
}

//fun Componente.toComponenteEntity(): ComponenteEntity {
//    return ComponenteEntity(
//        this.nombre, this.tipo, this.nombre
//    )
//}

fun EquipoEntity.toEquipo(): Equipo {
    return Equipo(
        this.nombreEquipo, this.nacionalidad, this.puestoFinal, emptyList()
    )
}

fun Equipo.toEquipoEntity(): EquipoEntity {
    return EquipoEntity(
        this.nombreEquipo, this.nacionalidad, this.puestoFinal,
    )
}

