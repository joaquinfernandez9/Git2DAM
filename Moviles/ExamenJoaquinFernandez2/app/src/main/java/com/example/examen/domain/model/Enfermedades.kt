package com.example.examen.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Enfermedades(
    var nombre: String,
) : Parcelable