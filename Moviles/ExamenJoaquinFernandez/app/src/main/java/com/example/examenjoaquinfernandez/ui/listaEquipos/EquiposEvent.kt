package com.example.examenjoaquinfernandez.ui.listaEquipos

sealed interface EquiposEvent {
    class Delete(val nombre: String): EquiposEvent
    object GetAll: EquiposEvent
}