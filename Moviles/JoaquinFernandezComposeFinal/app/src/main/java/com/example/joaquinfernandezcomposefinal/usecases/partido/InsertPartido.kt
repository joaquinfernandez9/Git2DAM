package com.example.mundialjoaquinfernandez.usecases.partido

import com.example.mundialjoaquinfernandez.data.model.datamapper.toPartidoEntity
import com.example.mundialjoaquinfernandez.data.repository.PartidoRepository
import com.example.mundialjoaquinfernandez.model.Partido
import javax.inject.Inject

class InsertPartido @Inject constructor(private val partidoRepository: PartidoRepository){

        suspend fun invoke(partido: Partido) = partidoRepository.jugarPartido(partido.local, partido.visitante)

}