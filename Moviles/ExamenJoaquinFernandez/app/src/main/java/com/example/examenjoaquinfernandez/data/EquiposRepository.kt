package com.example.examenjoaquinfernandez.data

import com.example.examenjoaquinfernandez.data.dao.EquiposDao
import com.example.examenjoaquinfernandez.data.modelo.EquipoEntity
import com.example.examenjoaquinfernandez.data.modelo.datamappers.toEquipo
import javax.inject.Inject

class EquiposRepository @Inject constructor(
    val equiposDao: EquiposDao
){
    suspend fun addEquipo(equipoEntity: EquipoEntity) =equiposDao.insert(equipoEntity)
    suspend fun getAll() =equiposDao.getAll().map { it.toEquipo() }
    suspend fun delete(nombre: String) =equiposDao.delete(nombre)
    suspend fun get(nombre: String) =equiposDao.getNombre(nombre).toEquipo()



}