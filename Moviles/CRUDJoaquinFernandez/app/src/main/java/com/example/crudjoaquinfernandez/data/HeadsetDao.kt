package com.example.crudjoaquinfernandez.data

import androidx.room.*
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity

@Dao
interface HeadsetDao {
    @Query("SELECT * FROM headsets")
    fun getAll(): List<HeadsetEntity>

    @Query("SELECT * FROM headsets WHERE id = :id")
    fun getById(id: Int): HeadsetEntity

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(headset: HeadsetEntity)

    @Update
    fun update(headset: HeadsetEntity)

    @Delete
    fun delete(id: Int)
}