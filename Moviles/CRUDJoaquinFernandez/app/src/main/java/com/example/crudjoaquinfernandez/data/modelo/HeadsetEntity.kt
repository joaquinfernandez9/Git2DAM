package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.crudjoaquinfernandez.data.ConstData

@Entity(
    tableName = ConstData.tableName,
    indices = [Index(value = ["id"], unique = true)]
)

data class HeadsetEntity(


    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = ConstData.bluetooth)
    val bluetooth: Int,
    @ColumnInfo(name = ConstData.name)
    val nombre: String,
    @ColumnInfo(name = ConstData.mic)
    val mic: Int,
)