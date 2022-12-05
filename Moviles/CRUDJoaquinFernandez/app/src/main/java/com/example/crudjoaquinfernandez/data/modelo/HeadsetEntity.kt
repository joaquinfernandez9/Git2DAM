package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.crudjoaquinfernandez.data.ConstData

@Entity(
    tableName = ConstData.TABLE_NAME,
    indices = [Index(value = [ConstData.id], unique = true)],
)
data class HeadsetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = ConstData.BLUETOOTH)
    val bluetooth: Int,
    @ColumnInfo(name = ConstData.name)
    val nombre: String,
    @ColumnInfo(name = ConstData.mic)
    val mic: Int,

)