package com.example.joaquinfernandezcomposefinal.domain.usecases

import com.example.joaquinfernandezcomposefinal.data.repository.EquiposRepository
import com.example.mundialjoaquinfernandez.data.repository.PartidoRepository
import javax.inject.Inject

class PlayMatch @Inject constructor(private val repo: PartidoRepository) {
    suspend fun invoke(equipoLocal: String, equipoVisitante: String) = repo.jugarPartido(equipoLocal, equipoVisitante)
}

class GetAllTeams @Inject constructor(private val repo: EquiposRepository) {
    suspend fun invoke() = repo.getAllEquipos()
}

class GetLastMatch @Inject constructor(private val repo: PartidoRepository) {
    suspend fun invoke() = repo.getLastMatch()
}

class GetAllMatches @Inject constructor(private val repo: PartidoRepository) {
    suspend fun invoke() = repo.getAllMatches()
}