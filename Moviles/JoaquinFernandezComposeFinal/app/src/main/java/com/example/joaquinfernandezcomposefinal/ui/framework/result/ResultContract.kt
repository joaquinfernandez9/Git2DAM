package com.example.joaquinfernandezcomposefinal.ui.framework.result

import com.example.joaquinfernandezcomposefinal.domain.model.Partido

interface ResultContract {

    data class State(val partido: Partido = Partido())

    sealed interface    Event {
        object GetLast: Event
    }
}