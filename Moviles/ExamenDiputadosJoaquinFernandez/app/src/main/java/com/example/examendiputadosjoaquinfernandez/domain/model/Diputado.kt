package com.example.examendiputadosjoaquinfernandez.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.UUID

@Parcelize
data class Diputado (
    val id: UUID,
    val nombre: String,
    val fecha_entrada: String,
    val corrupto: Boolean,
    val id_partido: UUID,
    val causasConf: List<CausasConfirmadas>,
    val causasSupuestas: List<CausasSupuestas>,
        ) : Parcelable