package com.example.examen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examen.data.local.dao.HospitalesDao
import com.example.examen.data.local.dao.PacientesDao
import com.example.examen.data.model.HospitalEntity
import com.example.examen.data.model.PacienteEntity

@Database(entities = [HospitalEntity::class, PacienteEntity::class], version = 1)
abstract class HospitalesDB: RoomDatabase() {
    abstract fun hospitalDao(): HospitalesDao
    abstract fun pacientesDao(): PacientesDao



}