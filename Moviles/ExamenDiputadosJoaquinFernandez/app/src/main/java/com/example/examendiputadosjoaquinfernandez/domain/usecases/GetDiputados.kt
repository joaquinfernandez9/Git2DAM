package com.example.examendiputadosjoaquinfernandez.domain.usecases

import com.example.examendiputadosjoaquinfernandez.data.local.repos.DiputadoRepository
import javax.inject.Inject

class GetDiputados @Inject constructor(
    private val repo: DiputadoRepository,
) {
    fun invoke(id:String) = repo.listDiputados(id)

    fun invoke() = repo.listDiputados()

}