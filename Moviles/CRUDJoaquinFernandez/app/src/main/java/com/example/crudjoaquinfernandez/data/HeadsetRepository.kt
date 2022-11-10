package com.example.crudjoaquinfernandez.data

import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import com.example.crudjoaquinfernandez.data.modelo.toHeadset
import com.example.crudjoaquinfernandez.domain.model.Headset

class HeadsetRepository (private val headsetDao: HeadsetDao) {
    fun addHeadset(headset: HeadsetEntity) = headsetDao.insert(headset)
    fun updateHeadset(headset: HeadsetEntity) = headsetDao.update(headset)
    fun getHeadset(id: Int) = headsetDao.getById(id).toHeadset()
    fun getAllHeadsets() = headsetDao.getAll().map { it.toHeadset() }
    fun removeHeadset(id: Int) = headsetDao.delete(id)
}
