package com.example.crudjoaquinfernandez.data

import androidx.room.*
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity

@Dao
interface HeadsetDao {
    @Query("SELECT * FROM cascos")
    suspend fun getAll(): List<HeadsetEntity>

    @Query("SELECT * FROM cascos WHERE id = :id")
    suspend fun getById(id: Int): HeadsetEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(headset: HeadsetEntity)

    @Update
    suspend fun update(headset: HeadsetEntity)

    @Query("DELETE FROM cascos WHERE id = :id")
    suspend fun delete(id: Int)
}