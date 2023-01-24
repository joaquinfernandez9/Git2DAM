package com.example.mundialjoaquinfernandez.ui.pantallas.listaJugadores

import com.example.mundialjoaquinfernandez.model.Jugador

sealed interface ListaJugadoresEvent {
    class GetAll(val nombre: String): ListaJugadoresEvent
    class DeleteJugador(val nombre: String): ListaJugadoresEvent
    //insert
    class InsertJugador(val jugador: Jugador, val equipo:String): ListaJugadoresEvent
}