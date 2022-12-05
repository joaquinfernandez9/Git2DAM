package com.example.crudjoaquinfernandez.data

import com.example.crudjoaquinfernandez.data.dao.StoresDao
import com.example.crudjoaquinfernandez.data.modelo.StoreEntity
import com.example.crudjoaquinfernandez.data.modelo.datamappers.toStore
import javax.inject.Inject

class StoreRepository @Inject constructor(private val dao: StoresDao) {
    suspend fun getAllStores()= dao.getAll().map { it.toStore() }
//    suspend fun getStore(storeName: String) = dao.getStore(storeName).toStore()
//    suspend fun deleteStore(storeName: String) = dao.deleteStore(storeName)
//    suspend fun deleteStoreWithHeadsets(storeName: String) = dao.deleteStoreWithHeadsets(storeName)
    suspend fun insertStore(storeEntity: StoreEntity) = dao.insertStore(storeEntity)

}