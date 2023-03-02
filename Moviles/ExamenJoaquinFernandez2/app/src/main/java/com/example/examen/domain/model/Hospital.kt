package com.example.examen.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Hospital(
    val id: UUID,
    val nombre: String,
    val numeroCamas: Int,
    val direccion: String,
    val pacientes: List<Paciente> ,
): Parcelable