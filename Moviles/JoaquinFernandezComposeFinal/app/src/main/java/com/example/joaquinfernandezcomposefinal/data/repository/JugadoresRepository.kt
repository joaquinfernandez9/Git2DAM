package com.example.mundialjoaquinfernandez.data.repository

import com.example.mundialjoaquinfernandez.data.dao.JugadoresDao
import com.example.mundialjoaquinfernandez.data.model.JugadorEntity
import com.example.joaquinfernandezcomposefinal.data.model.datamapper.toJugador
import javax.inject.Inject

class JugadoresRepository @Inject constructor(private val jugadoresDao: JugadoresDao) {

        //get all
        suspend fun getAllJugadores(nombreEquipo: String) = jugadoresDao.getAll(nombreEquipo).map { it.toJugador() }

        //delete
        suspend fun deleteJugador(nombre: String) = jugadoresDao.delete(nombre)

        //insert
        suspend fun insertJugador(jugador: JugadorEntity) = jugadoresDao.insert(jugador)


}