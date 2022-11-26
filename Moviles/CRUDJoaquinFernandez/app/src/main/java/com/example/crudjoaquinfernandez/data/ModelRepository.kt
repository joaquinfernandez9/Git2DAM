package com.example.crudjoaquinfernandez.data

import com.example.crudjoaquinfernandez.data.dao.HeadsetDao
import com.example.crudjoaquinfernandez.data.modelo.ModelEntity
import com.example.crudjoaquinfernandez.data.modelo.datamappers.toModel
import javax.inject.Inject

class ModelRepository @Inject constructor(private val modelDao: HeadsetDao) {
    suspend fun addModel(model: ModelEntity) = modelDao.insert(model)
    suspend fun getModel(id: Int) = modelDao.get(id).toModel()
    suspend fun getAllModels(idHeadset: Int) = modelDao.getAll(idHeadset).map { it.toModel() }
    suspend fun removeModel(id: ModelEntity) = modelDao.deleteModel(id)
    suspend fun removeAllModels(idHeadset: Int) = modelDao.deleteAllModels(idHeadset)
}