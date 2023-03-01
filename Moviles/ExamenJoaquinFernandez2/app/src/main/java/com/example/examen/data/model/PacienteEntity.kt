package com.example.examen.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examen.domain.model.Paciente
import java.util.UUID

@Entity
data class PacienteEntity(
    @PrimaryKey
    val id: UUID,
    val nombre: String,
    val dni: String,
//    val enfermedades: List<String>
)
fun PacienteEntity.toPaciente(): Paciente =
    Paciente(
        id = id,
        nombre = nombre,
        dni = dni,
    )

fun Paciente.toPacienteEntity(): PacienteEntity =
    PacienteEntity(
        id = id,
        nombre = nombre,
        dni = dni,
    )