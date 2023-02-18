package com.example.composecositas.domain.model


data class Movie (val id: Int, val titulo:String) {
    constructor(titulo: String) : this(0, titulo)
    constructor() : this(0, "")
}