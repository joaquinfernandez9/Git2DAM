package com.example.crudjoaquinfernandez.data.modelo.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.crudjoaquinfernandez.data.modelo.HeadsetEntity
import com.example.crudjoaquinfernandez.data.modelo.StoreEntity

data class StoresWithHeadsets (
    @Embedded val store: StoreEntity,
    @Relation(
        parentColumn = "storeName",
        entityColumn = "headsetId",
        associateBy = Junction(StoreHeadsetCrossRef::class)
    )
    val headsets: List<HeadsetEntity>
        )