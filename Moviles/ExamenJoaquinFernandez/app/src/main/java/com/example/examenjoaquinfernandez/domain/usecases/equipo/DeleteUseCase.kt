package com.example.examenjoaquinfernandez.domain.usecases.equipo

import com.example.examenjoaquinfernandez.data.EquiposRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(private val repo: EquiposRepository) {
    suspend operator fun invoke(nombre: String) =
        repo.delete(nombre)
}