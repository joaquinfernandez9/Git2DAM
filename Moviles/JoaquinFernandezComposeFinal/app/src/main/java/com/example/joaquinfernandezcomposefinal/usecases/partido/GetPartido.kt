package com.example.mundialjoaquinfernandez.usecases.partido

import com.example.mundialjoaquinfernandez.data.repository.PartidoRepository
import javax.inject.Inject

class GetPartido @Inject constructor(private val partidoRepository: PartidoRepository){

    suspend fun invoke(nombreLocal: String, nombreVisitante: String) = partidoRepository.getPartido(nombreLocal, nombreVisitante)

}