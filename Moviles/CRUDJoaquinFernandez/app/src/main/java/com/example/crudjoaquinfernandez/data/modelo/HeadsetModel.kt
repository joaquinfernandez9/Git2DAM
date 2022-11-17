package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.Embedded
import androidx.room.Relation

data class HeadsetModel(
    @Embedded val headset: HeadsetEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val mic: List<ModelEntity>?

)
