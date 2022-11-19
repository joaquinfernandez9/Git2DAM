package com.example.crudjoaquinfernandez.domain.usecases.model

import com.example.crudjoaquinfernandez.data.ModelRepository
import javax.inject.Inject

class GetAllModelsUseCase @Inject constructor(private val repo: ModelRepository) {
    suspend fun invoke(idHeadset: Int) =
        repo.getAllModels(idHeadset)

}