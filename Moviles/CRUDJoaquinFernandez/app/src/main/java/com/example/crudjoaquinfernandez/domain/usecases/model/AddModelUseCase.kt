package com.example.crudjoaquinfernandez.domain.usecases.model

import com.example.crudjoaquinfernandez.data.ModelRepository
import com.example.crudjoaquinfernandez.data.modelo.toModelEntity
import com.example.crudjoaquinfernandez.domain.model.Model
import javax.inject.Inject

class AddModelUseCase @Inject constructor(private val repo: ModelRepository) {
    suspend fun invoke(model: Model) =
        repo.addModel(model.toModelEntity())


}