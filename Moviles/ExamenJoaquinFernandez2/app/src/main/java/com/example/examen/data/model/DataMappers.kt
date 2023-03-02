package com.example.examen.data.model

import com.example.examen.domain.model.Hospital
import com.example.examen.domain.model.Paciente

fun HospitalEntity.toHospital() : Hospital {
    return Hospital(
        id,
        nombre,
        numeroCamas,
        direccion,
        arrayListOf()
    )
}

fun Hospital.toHospitalEntity(): HospitalEntity {
    return HospitalEntity(
        this.id,
        this.nombre,
        this.numeroCamas,
        this.direccion,
    )
}

fun PacienteEntity.toPacientes() : Paciente = Paciente(
    this.id,
    this.nombre,
    this.dni,
    arrayListOf()
)

fun Paciente.toPacientesEntity(): PacienteEntity = PacienteEntity(
    this.id,
    this.nombre,
    this.dni,
)