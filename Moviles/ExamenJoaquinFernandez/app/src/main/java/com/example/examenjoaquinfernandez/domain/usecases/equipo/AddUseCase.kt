package com.example.examenjoaquinfernandez.domain.usecases.equipo

import com.example.examenjoaquinfernandez.data.EquiposRepository
import com.example.examenjoaquinfernandez.data.modelo.datamappers.toEquipoEntity
import com.example.examenjoaquinfernandez.domain.model.Equipo
import javax.inject.Inject

class AddUseCase @Inject constructor(private val repo: EquiposRepository){
    suspend operator fun invoke(equipo : Equipo) =
        repo.addEquipo(equipo.toEquipoEntity())
}