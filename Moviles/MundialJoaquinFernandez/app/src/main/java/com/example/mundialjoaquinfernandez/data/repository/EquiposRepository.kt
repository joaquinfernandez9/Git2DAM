package com.example.mundialjoaquinfernandez.data.repository

import com.example.mundialjoaquinfernandez.data.dao.EquiposDao
import com.example.mundialjoaquinfernandez.data.model.datamapper.toEquipo
import javax.inject.Inject

class EquiposRepository @Inject constructor(private val equiposDao: EquiposDao) {

    //get all
    suspend fun getAllEquipos() = equiposDao.getAllEquipos().map { it.toEquipo() }


}