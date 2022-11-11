package com.example.crudjoaquinfernandez.data

import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import com.example.crudjoaquinfernandez.data.modelo.toHeadset
import com.example.crudjoaquinfernandez.domain.model.Headset

class HeadsetRepository (private val headsetDao: HeadsetDao) {
    suspend fun addHeadset(headset: HeadsetEntity) = headsetDao.insert(headset)
    suspend fun updateHeadset(headset: HeadsetEntity) = headsetDao.update(headset)
    suspend fun getHeadset(id: Int) = headsetDao.getById(id)?.toHeadset()
    suspend fun getAllHeadsets() = headsetDao.getAll().map { it.toHeadset() }
    suspend fun removeHeadset(id: Int) = headsetDao.delete(id)
}
