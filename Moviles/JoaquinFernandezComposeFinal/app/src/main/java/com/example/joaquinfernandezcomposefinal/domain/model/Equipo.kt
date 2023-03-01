package com.example.joaquinfernandezcomposefinal.domain.model

data class Equipo(
    val nombre: String,
    val jugadores: List<Jugador>,
){
    constructor() : this("", emptyList())
    //tostring
    override fun toString(): String {
        return nombre
    }
}

