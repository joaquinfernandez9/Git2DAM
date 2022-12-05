package com.example.examenjoaquinfernandez.domain.model

data class Equipo (
    val nombreEquipo: String,
    val nacionalidad: String,
    val puestoFinal: Int,
    var componentes: List<Componente>
        )