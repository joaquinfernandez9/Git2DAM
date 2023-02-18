package com.example.composecositas.framework.ui.list

import com.example.composecositas.domain.model.Movie

interface ListContract {
    sealed interface Event {
        object GetAll : Event
        data class Delete(val movie: Movie) : Event
    }
}