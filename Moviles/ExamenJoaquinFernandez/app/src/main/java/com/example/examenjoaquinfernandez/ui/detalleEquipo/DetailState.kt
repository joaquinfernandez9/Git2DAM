package com.example.examenjoaquinfernandez.ui.detalleEquipo

import com.example.examenjoaquinfernandez.domain.model.Equipo

data class DetailState (
    val equipo: Equipo? =null,
    val error: String? = null,
)