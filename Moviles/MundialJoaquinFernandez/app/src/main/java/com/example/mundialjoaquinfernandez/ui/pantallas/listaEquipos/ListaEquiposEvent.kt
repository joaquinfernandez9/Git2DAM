package com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos

sealed interface ListaEquiposEvent {
    object GetAll: ListaEquiposEvent
}