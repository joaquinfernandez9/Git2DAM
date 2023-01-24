package com.example.mundialjoaquinfernandez.usecases.jugadores

import com.example.mundialjoaquinfernandez.data.repository.JugadoresRepository
import javax.inject.Inject

class GetAllJugadores @Inject constructor(private val jugadoresRepository: JugadoresRepository) {

    suspend fun invoke(nombreEquipo: String) = jugadoresRepository.getAllJugadores(nombreEquipo)


}