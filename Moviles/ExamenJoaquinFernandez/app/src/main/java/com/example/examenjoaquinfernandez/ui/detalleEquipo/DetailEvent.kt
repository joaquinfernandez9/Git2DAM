package com.example.examenjoaquinfernandez.ui.detalleEquipo

interface DetailEvent {
    class GetEquipo(val nombre: String?) : DetailEvent
    class GetAllComponents(val nombre: String?) : DetailEvent
}