package com.example.joaquinfernandezcomposefinal.data.repository

import com.example.mundialjoaquinfernandez.data.dao.EquiposDao
import com.example.joaquinfernandezcomposefinal.data.model.datamapper.toEquipo
import javax.inject.Inject

class EquiposRepository @Inject constructor(private val equiposDao: EquiposDao) {
    suspend fun getAllEquipos() = equiposDao.getAllEquipos().map { it.toEquipo() }
}

