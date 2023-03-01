package com.example.examen.domain.model

import java.util.UUID

data class Hospital(
    val id: UUID,
    val nombre: String,
    val numeroCamas: Int,
    val direccion: String,
//    val pacientes: List<Paciente> ,
) {
}