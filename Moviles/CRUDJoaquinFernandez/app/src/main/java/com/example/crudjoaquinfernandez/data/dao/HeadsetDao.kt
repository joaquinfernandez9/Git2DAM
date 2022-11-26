package com.example.crudjoaquinfernandez.data.dao

import androidx.room.*
import com.example.crudjoaquinfernandez.data.ConstData
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import com.example.crudjoaquinfernandez.data.modelo.ModelEntity

@Dao
interface HeadsetDao {
    @Query(ConstData.getAll)
    suspend fun getAll(): List<HeadsetEntity>

    @Query(ConstData.getById)
    suspend fun getById(id: Int): HeadsetEntity

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(headset: HeadsetEntity)

    @Update
    suspend fun update(headset: HeadsetEntity)

    @Query(ConstData.delete)
    suspend fun deleteHeadset(id: Int)

    @Query(ConstData.deleteAllModels)
    suspend fun deleteAllModels(idHeadset: Int)

    @Transaction
    suspend fun borrarHeadsetConModelos(idHeadset: Int){
        deleteAllModels(idHeadset)
        deleteHeadset(idHeadset)
    }

    @Query(ConstData.getAllModels)
    suspend fun getAll(id: Int): List<ModelEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: ModelEntity)

    @Delete
    suspend fun deleteModel(model: ModelEntity)

    @Query(ConstData.getModel)
    suspend fun get(id: Int): ModelEntity


}