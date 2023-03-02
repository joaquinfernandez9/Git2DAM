package com.example.examen.framework.xml.getAllPatients

import com.example.examen.domain.model.Paciente

interface PatientsContract {

    sealed interface Event {
        object Cargar: Event
    }


    data class State(
        val pacientes: List<Paciente>? = emptyList(),
        val error: String? = null,
        val isLoading: Boolean = true,
    )

}