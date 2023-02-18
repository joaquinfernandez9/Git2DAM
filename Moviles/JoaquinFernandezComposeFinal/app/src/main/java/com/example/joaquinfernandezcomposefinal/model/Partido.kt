package com.example.mundialjoaquinfernandez.model

import java.time.LocalDate

data class Partido (
    val id: Int,
    val local: String,
    val visitante: String,
    val golesLocal: Int,
    val golesVisitante: Int,
    val fecha: LocalDate,
        ) {
    constructor(local: String, visitante: String) : this(0, local, visitante, 0, 0, LocalDate.now())
    constructor(id: Int, golesLocal: Int, golesVisitante: Int, fecha: String) : this(id, "", "", golesLocal, golesVisitante, LocalDate.parse(fecha))

}