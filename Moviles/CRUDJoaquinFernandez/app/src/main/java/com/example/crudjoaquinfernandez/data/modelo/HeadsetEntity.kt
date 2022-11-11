package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cascos")
data class HeadsetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "bluetooth")
    val bluetooth: Int,
    @ColumnInfo(name = "name")
    val nombre: String,
    @ColumnInfo(name = "mic")
    val mic: Int,

)