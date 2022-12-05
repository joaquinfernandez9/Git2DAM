package com.example.crudjoaquinfernandez.data.modelo.relations

import androidx.room.Entity

@Entity(primaryKeys = ["storeName", "headsetModel"])
data class StoreHeadsetCrossRef(
    val storeName: String,
    val headsetId: Int,
)