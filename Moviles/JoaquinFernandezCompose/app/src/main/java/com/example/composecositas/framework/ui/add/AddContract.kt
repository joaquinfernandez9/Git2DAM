package com.example.composecositas.framework.ui.add

import com.example.composecositas.domain.model.Movie

interface AddContract {

    data class State(val movie: Movie = Movie())

    sealed interface Event {
        data class Add(val movie: Movie) : Event
    }
}