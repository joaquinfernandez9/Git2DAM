package com.example.colocacioncosas.ui.pantallaMain

import com.example.colocacioncosas.domain.modelo.Persona

data class MainState (
    val persona: Persona,
    val error: String? = null,
)