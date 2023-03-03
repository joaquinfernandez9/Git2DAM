package com.example.examendiputadosjoaquinfernandez.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Diputado (
    val id: UUID,
    val nombre: String,
    val corrupto: Boolean,
    val idPartido: UUID,
    val fechaEntradaCongreso: String,
    val causasConfirmadas: List<String>?,
    val causasSupuestas: List<String>?,
        ) : Parcelable