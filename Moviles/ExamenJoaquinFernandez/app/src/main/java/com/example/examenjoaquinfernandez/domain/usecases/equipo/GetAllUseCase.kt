package com.example.examenjoaquinfernandez.domain.usecases.equipo

import com.example.examenjoaquinfernandez.data.EquiposRepository
import javax.inject.Inject

class GetAllUseCase @Inject constructor(private val repo: EquiposRepository){
    suspend operator fun invoke() = repo.getAll()

}
