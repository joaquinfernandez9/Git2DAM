package com.example.mundialjoaquinfernandez.model

data class Equipo(
    val nombre: String,
    val jugadores: List<Jugador>,
){
    //tostring
    override fun toString(): String {
        return nombre
    }
}

