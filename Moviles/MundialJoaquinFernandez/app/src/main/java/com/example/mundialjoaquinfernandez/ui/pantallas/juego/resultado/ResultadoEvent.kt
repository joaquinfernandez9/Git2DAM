package com.example.mundialjoaquinfernandez.ui.pantallas.juego.resultado

import com.example.mundialjoaquinfernandez.model.Partido

sealed interface ResultadoEvent {
    class JugarPartido(val partido: Partido): ResultadoEvent
    class GetPartido(val nombreLocal: String, val nombreVisitante: String): ResultadoEvent
}