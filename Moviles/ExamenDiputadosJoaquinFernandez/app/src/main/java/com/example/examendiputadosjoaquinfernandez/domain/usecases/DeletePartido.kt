package com.example.examendiputadosjoaquinfernandez.domain.usecases

import com.example.examendiputadosjoaquinfernandez.data.local.repos.PartidoRepository
import javax.inject.Inject

class DeletePartido @Inject constructor(
    private val repo: PartidoRepository,
) {
    fun invoke(id: String) = repo.deletePartido(id)
}
