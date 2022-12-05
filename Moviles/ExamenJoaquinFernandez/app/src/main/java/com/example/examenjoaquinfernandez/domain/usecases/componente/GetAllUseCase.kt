package com.example.examenjoaquinfernandez.domain.usecases.componente

import com.example.examenjoaquinfernandez.data.ComponenteRepository
import javax.inject.Inject

class GetAllUseCase
@Inject constructor(private val repo: ComponenteRepository){
    suspend fun invoke(nombreEquipo: String) =
        repo.getComponentes(nombreEquipo)
}