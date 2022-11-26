package com.example.crudjoaquinfernandez.data.modelo.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.crudjoaquinfernandez.data.ConstData
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import com.example.crudjoaquinfernandez.data.modelo.ModelEntity

data class HeadsetModel(
    @Embedded val headset: HeadsetEntity,
    @Relation(
        parentColumn = ConstData.modelId,
        entityColumn = ConstData.headsetId
    )
    val models: List<ModelEntity>?

)
