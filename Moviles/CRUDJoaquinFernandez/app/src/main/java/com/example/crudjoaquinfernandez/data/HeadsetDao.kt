package com.example.crudjoaquinfernandez.data

import androidx.room.*
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import com.example.crudjoaquinfernandez.ui.mainScreen.Const

@Dao
interface HeadsetDao {
    @Query(ConstData.getAll)
    suspend fun getAll(): List<HeadsetEntity>

    @Query(ConstData.getById)
    suspend fun getById(id: Int): HeadsetEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(headset: HeadsetEntity)

    @Update
    suspend fun update(headset: HeadsetEntity)

    @Query(ConstData.delete)
    suspend fun delete(id: Int)
}