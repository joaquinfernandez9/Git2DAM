package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.crudjoaquinfernandez.data.ConstData

data class HeadsetModel(
    @Embedded val headset: HeadsetEntity,
    @Relation(
        parentColumn = ConstData.modelId,
        entityColumn = ConstData.headsetId
    )
    val models: List<ModelEntity>?

)
