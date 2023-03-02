package com.example.examen.framework.compose.litsHospitals

import com.example.examen.domain.model.Hospital
import com.example.examen.domain.model.Paciente

interface HospitalsContract {
    data class State(
        var hospitales: List<Hospital>? = emptyList(),
        val pacientes: List<Paciente> = emptyList(),
        var isLoading: Boolean = true,
        var error: String? = null
    )

    sealed interface Event {
        object GetHospitales: Event
//        data class GetPacientes(val hospital: Hospital): Event
//        data class DeleteHospital(val id: String): Event
    }
}