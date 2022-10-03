package com.example.colocacioncosas.data

import com.example.colocacioncosas.domain.modelo.Persona

class Repository {

    fun addPersona(persona: Persona): Boolean {
        return lista.add(persona)
    }

    fun getLista(): List<Persona> {
        return lista
    }

    //bloque estatico, solo si hay parametros
    companion object{

        private val lista = mutableListOf<Persona>()
        fun createSingleton(): Repository = Repository()

//        fun createSignleton2(): Repository {
//            return Repository()
//        }
    }

    //para cargar cosas (constructor)
    init {
        lista.add(Persona("Juan"))
        lista.add(Persona("Maria"))
        lista.add(Persona("Pedro"))
    }

}