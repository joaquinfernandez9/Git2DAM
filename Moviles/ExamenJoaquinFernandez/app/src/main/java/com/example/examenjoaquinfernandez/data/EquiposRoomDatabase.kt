package com.example.examenjoaquinfernandez.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examenjoaquinfernandez.data.dao.ComponentesDao
import com.example.examenjoaquinfernandez.data.dao.EquiposDao
import com.example.examenjoaquinfernandez.data.modelo.ComponenteEntity
import com.example.examenjoaquinfernandez.data.modelo.EquipoEntity

@Database(entities = [EquipoEntity::class, ComponenteEntity::class],
    version = 1,
    exportSchema = true)
abstract class EquiposRoomDatabase: RoomDatabase(){
    abstract fun equiposDao(): EquiposDao
    abstract fun componentesDao(): ComponentesDao

}