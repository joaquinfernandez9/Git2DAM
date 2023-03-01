package com.example.examen.framework.xml.initScreen

import com.example.examen.domain.model.Hospital

interface InitContract {

    sealed class Event {
        object Cargar: Event()
    }

    data class State(
        val hospitales: List<Hospital> = emptyList(),
        val error: String? = null,
    )
}