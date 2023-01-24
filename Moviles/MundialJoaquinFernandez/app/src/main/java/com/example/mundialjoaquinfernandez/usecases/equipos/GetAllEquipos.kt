package com.example.mundialjoaquinfernandez.usecases.equipos

import com.example.mundialjoaquinfernandez.data.repository.EquiposRepository
import javax.inject.Inject

class GetAllEquipos @Inject constructor(private val equiposRepository: EquiposRepository) {

    suspend fun invoke() = equiposRepository.getAllEquipos()


}