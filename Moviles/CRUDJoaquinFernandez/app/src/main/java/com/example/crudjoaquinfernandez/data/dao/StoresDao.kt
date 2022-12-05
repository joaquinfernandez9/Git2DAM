package com.example.crudjoaquinfernandez.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.crudjoaquinfernandez.data.modelo.StoreEntity

@Dao
interface StoresDao {
    @Query("SELECT * FROM stores")
    suspend fun getAll(): List<StoreEntity>

    @Query("select * from stores where storeName = :storeName")
    suspend fun getStore(storeName: String): StoreEntity

    @Query("delete from stores where storeName = :storeName")
    suspend fun deleteStore(storeName: String)


    @Transaction
    suspend fun deleteStoreWithHeadsets(storeName: String) {
        deleteStore(storeName)
    }

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertStore(storeEntity: StoreEntity)


}