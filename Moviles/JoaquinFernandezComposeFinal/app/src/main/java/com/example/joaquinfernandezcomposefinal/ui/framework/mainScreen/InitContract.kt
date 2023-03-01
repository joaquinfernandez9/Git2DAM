package com.example.joaquinfernandezcomposefinal.ui.framework.mainScreen

import com.example.joaquinfernandezcomposefinal.domain.model.Equipo

interface InitContract {

    data class State(val equipos: List<Equipo> = emptyList())

    sealed interface Event {
        object Init: Event
    }
}