package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.crudjoaquinfernandez.data.modelo.relations.HeadsetsWithStores

@Entity(tableName = "stores",)
data class StoreEntity (
    @PrimaryKey(autoGenerate = false)
    val storeName: String,

        )