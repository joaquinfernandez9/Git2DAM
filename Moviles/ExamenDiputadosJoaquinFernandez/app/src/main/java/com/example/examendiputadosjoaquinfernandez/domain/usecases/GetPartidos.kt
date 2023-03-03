package com.example.examendiputadosjoaquinfernandez.domain.usecases

import com.example.examendiputadosjoaquinfernandez.data.local.repos.PartidoRepository
import javax.inject.Inject

class GetPartidos @Inject constructor(
    private val repo: PartidoRepository,
) {
    fun invoke() = repo.listPartidos()
}