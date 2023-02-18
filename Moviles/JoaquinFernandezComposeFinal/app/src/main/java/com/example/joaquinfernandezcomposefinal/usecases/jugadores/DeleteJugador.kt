package com.example.mundialjoaquinfernandez.usecases.jugadores

import com.example.mundialjoaquinfernandez.data.repository.JugadoresRepository
import javax.inject.Inject

class DeleteJugador @Inject constructor(private val jugadoresRepository: JugadoresRepository) {

        suspend fun invoke(nombre: String) = jugadoresRepository.deleteJugador(nombre)



}