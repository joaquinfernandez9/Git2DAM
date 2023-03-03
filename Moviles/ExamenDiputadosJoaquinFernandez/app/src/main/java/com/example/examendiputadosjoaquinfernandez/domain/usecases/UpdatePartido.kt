package com.example.examendiputadosjoaquinfernandez.domain.usecases

import com.example.examendiputadosjoaquinfernandez.data.local.repos.PartidoRepository
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido
import javax.inject.Inject

class UpdatePartido @Inject constructor(
    private val repo: PartidoRepository,
) {
    fun invoke(partido: Partido) = repo.updatePartido(partido)
}