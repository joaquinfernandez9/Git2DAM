package com.example.examen.framework.xml.initScreen

import com.example.examen.domain.model.Hospital
import com.example.examen.domain.model.Paciente

interface InitContract {

    sealed interface Event {
        object Cargar: Event
        data class GetPacientes(val hospital: Hospital): Event
    }

    data class State(
        val hospitales: List<Hospital>? = emptyList(),
        val pacientes: List<Paciente> = emptyList(),
        val error: String? = null,
        val isLoading: Boolean = true,
    )
}