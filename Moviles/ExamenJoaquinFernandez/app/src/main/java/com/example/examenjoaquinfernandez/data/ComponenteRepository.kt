package com.example.examenjoaquinfernandez.data

import com.example.examenjoaquinfernandez.data.dao.ComponentesDao
import com.example.examenjoaquinfernandez.data.modelo.datamappers.toComponente
import javax.inject.Inject

class ComponenteRepository
@Inject constructor(private val dao: ComponentesDao){
    suspend fun getComponentes(nombreEquipo : String) = dao.getComponentes(nombreEquipo).map { it.toComponente() }
}