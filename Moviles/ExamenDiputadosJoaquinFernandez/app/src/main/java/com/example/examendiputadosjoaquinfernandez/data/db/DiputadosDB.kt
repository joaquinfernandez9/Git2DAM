package com.example.examendiputadosjoaquinfernandez.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examendiputadosjoaquinfernandez.data.local.dao.PartidoDao
import com.example.examendiputadosjoaquinfernandez.data.model.DiputadoEntity
import com.example.examendiputadosjoaquinfernandez.data.model.PartidoEntity
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido

@Database(entities = [PartidoEntity::class, DiputadoEntity::class],  version = 1)
abstract class DiputadosDB : RoomDatabase(){
    abstract fun partidoDao(): PartidoDao
}