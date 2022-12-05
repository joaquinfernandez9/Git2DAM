package com.example.examenjoaquinfernandez.domain.usecases.equipo

import com.example.examenjoaquinfernandez.data.EquiposRepository
import javax.inject.Inject

class GetUseCase @Inject constructor(
    private val repo: EquiposRepository
){
    suspend operator fun invoke(nombre: String) =
        repo.get(nombre)

}