package com.example.joaquinfernandezcomposefinal.data.model.datamapper

import com.example.mundialjoaquinfernandez.data.model.EquipoConJugadores
import com.example.mundialjoaquinfernandez.data.model.EquipoEntity
import com.example.mundialjoaquinfernandez.data.model.JugadorEntity
import com.example.mundialjoaquinfernandez.data.model.PartidoEntity
import com.example.joaquinfernandezcomposefinal.domain.model.Equipo
import com.example.joaquinfernandezcomposefinal.domain.model.Jugador
import com.example.joaquinfernandezcomposefinal.domain.model.Partido

fun EquipoConJugadores.toEquipo(): Equipo {
    return Equipo(
        nombre = this.equipo.nombreEquipo,
        jugadores = this.jugadores.map { it.toJugador() }
    )
}

fun EquipoEntity.toEquipo(): Equipo {
    return Equipo(
        nombre = this.nombreEquipo,
        jugadores = emptyList()
    )
}

fun Equipo.toEquipoEntity(): EquipoEntity {
    return EquipoEntity(
        nombreEquipo = nombre,
    )
}

fun JugadorEntity.toJugador(): Jugador {
    return Jugador(
        nombre = nombreJugador,
        dorsal = dorsal,
        posicion = posicion,
    )
}

fun Jugador.toJuagadorEntity(equipo: String): JugadorEntity {

    return JugadorEntity(
        nombreJugador = nombre,
        dorsal = dorsal,
        posicion = posicion,
        equipo = equipo
    )
}

//partido to partido entity
fun Partido.toPartidoEntity(): PartidoEntity {
    return PartidoEntity(
        golesLocal = golesLocal,
        golesVisitante = golesVisitante,
        fecha = fecha.toString(),
    )
}

//partido entity to partido
fun PartidoEntity.toPartido(): Partido {
    return Partido(
        id = id,
        golesLocal = golLocal,
        golesVisitante = golVisitante,
        fecha = fecha
    )
}


