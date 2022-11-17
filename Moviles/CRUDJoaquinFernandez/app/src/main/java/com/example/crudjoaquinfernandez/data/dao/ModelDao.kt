package com.example.crudjoaquinfernandez.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.crudjoaquinfernandez.data.modelo.ModelEntity

@Dao
interface ModelDao {

    @Query("SELECT * FROM modelos")
    suspend fun getAll(): List<ModelEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: ModelEntity)

    @Query("DELETE FROM modelos WHERE id_model = :id")
    suspend fun delete(id: Int)

    @Query("select * from modelos where id_model = :id")
    suspend fun get(id: Int): ModelEntity

    @Update
    suspend fun update(item: ModelEntity)

}