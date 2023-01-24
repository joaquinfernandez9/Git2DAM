package com.example.flowsjoaquinfernandez.framework.series

import com.example.flowsjoaquinfernandez.domain.modelo.Series

interface SeriesContract {

    sealed class Event {
        object Cargar : Event()
        class Buscar(val nombre: String) : Event()
    }

    data class State(
        val series: List<Series> = emptyList(),
        val isLoading : Boolean = false,
        val error: String? = null,

        )
}