package com.example.examendiputadosjoaquinfernandez.framework.compose.diputados

import com.example.examendiputadosjoaquinfernandez.domain.model.Diputado
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido

interface DiputadosContract {
    sealed interface Event {
        object Cargar: Event
        data class GetDiputados(val id: String): Event
        data class GetDiputado(val dipu: String): Event
        object LimpiarError: Event
    }

    data class State(
        val partidos: List<Partido>? = emptyList(),
        val diputados: List<Diputado>? = emptyList(),
        val dipu: Diputado? = null,
        val error: String? = null,
        val isLoading: Boolean = true,
    )
}