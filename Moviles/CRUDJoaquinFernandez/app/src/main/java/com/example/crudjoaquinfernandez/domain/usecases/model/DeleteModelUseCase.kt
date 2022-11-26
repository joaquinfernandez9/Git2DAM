package com.example.crudjoaquinfernandez.domain.usecases.model

import com.example.crudjoaquinfernandez.data.ModelRepository
import com.example.crudjoaquinfernandez.data.modelo.datamappers.toModelEntity
import com.example.crudjoaquinfernandez.domain.model.Model
import javax.inject.Inject

class DeleteModelUseCase @Inject constructor(private val repo: ModelRepository) {
    suspend fun invoke(id: Model) =
        repo.removeModel(id.toModelEntity())

}