package com.example.examen.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Paciente(
    val id: String,
    val nombre: String,
    val dni: String,
    val enfermedades: List<Enfermedades>? = emptyList()
) : Parcelable