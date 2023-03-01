package com.example.examen.domain.model

import java.util.UUID

data class Paciente(
    val id: UUID,
    val nombre: String,
    val dni: String,
//    val enfermedades: List<String>
) {
}