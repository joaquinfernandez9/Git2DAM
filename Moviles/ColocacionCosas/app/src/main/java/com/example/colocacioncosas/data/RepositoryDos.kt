package com.example.colocacioncosas.data

import com.example.colocacioncosas.domain.modelo.Persona

//no se le puede meter parametros
object RepositoryDos {

    // mutable se modifica  pero list no
    private val lista = mutableListOf<Persona>()

    fun addPersona(persona: Persona) {
        lista.add(persona)
    }

    fun getLista(): List<Persona> {
        return lista
    }

}