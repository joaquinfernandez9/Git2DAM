package com.example.examen.framework.xml.detail

import com.example.examen.domain.model.Enfermedades
import com.example.examen.domain.model.Paciente

interface DetailContract {

    data class State(
        var pacientes: Paciente? = null,
        var enfermedades: List<Enfermedades>? = emptyList(),
        var error: String? = null,
        var isLoading: Boolean = true
    )

    sealed interface Event {
        // ver paciente
        data class GetPaciente(val id: String): Event
        // ver enfermedades
        data class VerEnfermedades(val paciente: Paciente): Event
        // update nombre paciente
        data class UpdatePaciente(val id: String, val paciente: Paciente): Event
        // añadir enfermedad
        data class PostEnfermedad(val id: String, val enfermedades: Enfermedades): Event
    }
}