package com.example.crudjoaquinfernandez.data.modelo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.crudjoaquinfernandez.data.modelo.relations.HeadsetsWithStores

@Entity(
    tableName = "stores",
    indices = [Index(value = ["storeName"], unique = true)],

)
data class StoreEntity(
    @PrimaryKey(autoGenerate = false)
    val storeName: String,
)