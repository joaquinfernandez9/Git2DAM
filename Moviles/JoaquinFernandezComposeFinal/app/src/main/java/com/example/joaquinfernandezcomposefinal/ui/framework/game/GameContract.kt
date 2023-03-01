package com.example.joaquinfernandezcomposefinal.ui.framework.game

import com.example.joaquinfernandezcomposefinal.domain.model.Equipo
import com.example.joaquinfernandezcomposefinal.domain.model.Partido

interface GameContract {
    data class State(val partido: Partido = Partido())

    sealed interface Event {
        object GetAll: Event
        data class Play(val local: String, val visitante: String) : Event
    }
}