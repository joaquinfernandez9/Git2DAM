package com.example.examendiputadosjoaquinfernandez.framework.xml.crud

import com.example.examendiputadosjoaquinfernandez.domain.model.Partido

interface Contract {
    sealed interface Event {
        object Cargar: Event
        data class PostPartido(val partido: String): Event
        data class DeletePartido(val id: String): Event
        data class UpdatePartido(val id: String, val nombre: String): Event
    }

    data class State(
        val partidos: List<Partido>? = emptyList(),
        val error: String? = null,
        val isLoading: Boolean = true,
    )
}