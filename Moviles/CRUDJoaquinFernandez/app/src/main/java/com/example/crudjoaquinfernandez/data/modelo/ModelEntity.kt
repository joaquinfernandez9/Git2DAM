package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.crudjoaquinfernandez.data.ConstData

@Entity(tableName = ConstData.modelos, foreignKeys = [
    ForeignKey(entity = HeadsetEntity::class,
        parentColumns = [ConstData.id],
        childColumns = [ConstData.headsetId])
])
data class ModelEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstData.idModel)
    val idModel: Int,
    @ColumnInfo(name = ConstData.model)
    val model: String,

    val headsetId : Int,
)
