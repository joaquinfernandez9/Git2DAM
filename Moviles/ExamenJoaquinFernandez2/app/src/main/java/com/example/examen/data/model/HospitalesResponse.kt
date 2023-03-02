package com.example.examen.data.model

import com.example.examen.domain.model.Paciente

data class HospitalesResponse (
    var id: Int,
    var nombre: String,
    var numeroCamas: String,
    var direccion: Double,
    var pacientes: List<Paciente>
        ){

}