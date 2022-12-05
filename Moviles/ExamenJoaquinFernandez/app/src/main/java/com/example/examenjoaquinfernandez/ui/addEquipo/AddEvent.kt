package com.example.examenjoaquinfernandez.ui.addEquipo

import com.example.examenjoaquinfernandez.domain.model.Equipo

sealed interface AddEvent {
    class AddEquipo(val equipo: Equipo): AddEvent
    class GetEquipo(val nombre: String): AddEvent
}