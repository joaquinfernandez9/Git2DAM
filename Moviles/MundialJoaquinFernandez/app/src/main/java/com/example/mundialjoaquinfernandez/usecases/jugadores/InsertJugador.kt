package com.example.mundialjoaquinfernandez.usecases.jugadores

import com.example.mundialjoaquinfernandez.data.model.JugadorEntity
import com.example.mundialjoaquinfernandez.data.model.datamapper.toJuagadorEntity
import com.example.mundialjoaquinfernandez.data.repository.JugadoresRepository
import com.example.mundialjoaquinfernandez.model.Jugador
import javax.inject.Inject

class InsertJugador @Inject constructor(private val jugadoresRepository: JugadoresRepository) {

    suspend fun invoke(jugador: Jugador, equipo:String) = jugadoresRepository.insertJugador(jugador.toJuagadorEntity(equipo))


}