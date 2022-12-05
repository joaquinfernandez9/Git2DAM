package com.example.examenjoaquinfernandez.ui.listaEquipos

import com.example.examenjoaquinfernandez.domain.model.Equipo

data class EquiposState (
    val list: List<Equipo> = emptyList(),
    val error: String? = null
        )