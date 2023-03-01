package com.example.mundialjoaquinfernandez.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mundialjoaquinfernandez.data.dao.EquiposDao
import com.example.mundialjoaquinfernandez.data.dao.JugadoresDao
import com.example.mundialjoaquinfernandez.data.dao.PartidoDao
import com.example.mundialjoaquinfernandez.data.model.EquipoEntity
import com.example.mundialjoaquinfernandez.data.model.JugadorEntity
import com.example.mundialjoaquinfernandez.data.model.PartidoEntity
import com.example.mundialjoaquinfernandez.data.model.PartidoEquipoCrossRef

@Database (
    entities = [EquipoEntity::class,
        JugadorEntity::class, PartidoEntity::class,
        PartidoEquipoCrossRef::class],
    version = 11,
    exportSchema = true,
        )
abstract class EquiposDatabase : RoomDatabase() {
    abstract fun jugadoresDao(): JugadoresDao
    abstract fun equiposDao(): EquiposDao
    abstract fun partidosDao(): PartidoDao
}