package com.example.crudjoaquinfernandez.domain.usecases.model

import com.example.crudjoaquinfernandez.data.ModelRepository
import javax.inject.Inject

class GetModelUseCase @Inject constructor(private val repo: ModelRepository) {
    suspend operator fun invoke(id: Int) = repo.getModel(id)
}
