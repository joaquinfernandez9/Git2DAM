package com.example.flowsjoaquinfernandez.framework.movies

import com.example.flowsjoaquinfernandez.domain.modelo.Movie

interface MovieContract {

    sealed class Event {
        object Cargar : Event()
    }

    data class State(
        val movies: List<Movie> = emptyList(),
        val isLoading : Boolean = false,
        val error: String? = null,

        )
}