package com.example.examen.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examen.domain.model.Hospital
import com.example.examen.domain.model.Paciente
import java.util.UUID

@Entity
data class HospitalEntity(
    @PrimaryKey
    val id: UUID,
    val nombre: String,
    val numeroCamas: Int,
    val direccion: String,
)

fun HospitalEntity.toHospital(): Hospital =
    Hospital(
        id = id,
        nombre = nombre,
        numeroCamas = numeroCamas,
        direccion = direccion,
    )

fun Hospital.toHospitalEntity(): HospitalEntity =
    HospitalEntity(
        id = id,
        nombre = nombre,
        numeroCamas = numeroCamas,
        direccion = direccion,
    )
