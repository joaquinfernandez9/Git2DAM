package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "modelos", foreignKeys = [
    ForeignKey(entity = HeadsetEntity::class,
        parentColumns = ["id"],
        childColumns = ["id"])
])
data class ModelEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "model")
    val model: String,
)
