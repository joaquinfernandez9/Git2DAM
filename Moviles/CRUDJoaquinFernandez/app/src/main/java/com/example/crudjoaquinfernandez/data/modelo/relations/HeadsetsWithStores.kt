package com.example.crudjoaquinfernandez.data.modelo.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import com.example.crudjoaquinfernandez.data.modelo.StoreEntity

data class HeadsetsWithStores(
    @Embedded val headset: HeadsetEntity,
    @Relation(
        parentColumn = "headsetId",
        entityColumn = "storeName",
        associateBy = Junction(StoreHeadsetCrossRef::class)
    )
    val stores: List<StoreEntity>
)