package com.example.crudjoaquinfernandez.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.crudjoaquinfernandez.data.modelo.StoreEntity

@Dao
interface StoresDao {
    @Query("SELECT * FROM stores")
    suspend fun getAll(): List<StoreEntity>

    @Query("select * from stores where storeName = :storeName")
    suspend fun getStore(storeName: String): StoreEntity

}