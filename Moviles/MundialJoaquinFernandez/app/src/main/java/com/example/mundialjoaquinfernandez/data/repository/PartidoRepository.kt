package com.example.mundialjoaquinfernandez.data.repository

import com.example.mundialjoaquinfernandez.data.dao.EquiposDao
import com.example.mundialjoaquinfernandez.data.dao.PartidoDao
import com.example.mundialjoaquinfernandez.data.model.PartidoConEquipos
import com.example.mundialjoaquinfernandez.data.model.PartidoEntity
import com.example.mundialjoaquinfernandez.data.model.datamapper.toEquipoEntity
import com.example.mundialjoaquinfernandez.data.model.datamapper.toPartido
import com.example.mundialjoaquinfernandez.model.Equipo
import com.example.mundialjoaquinfernandez.model.Partido
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PartidoRepository @Inject constructor(private val partidoDao: PartidoDao, private val equiposDao: EquiposDao) {

    suspend fun jugarPartido(equipoLocal: String, equipoVisitante: String):
            PartidoEntity {
        val partido =PartidoEntity(
            (0..5).random(),
            (0..5).random(),
            LocalDate.now().toString(),
        )

        val local = equiposDao.getEquipoByName(equipoLocal)
        val visitante = equiposDao.getEquipoByName(equipoVisitante)

        val partidoConEquipos = PartidoConEquipos(
            partido,
            listOf(local, visitante)
        )

        partidoDao.insertPartidoConSelecciones(partidoConEquipos)
        return partido
    }

    suspend fun getPartido(nombreLocal: String, nombreVisitante: String): Partido {
        val id =  partidoDao.getPartido(nombreLocal, nombreVisitante)
        return partidoDao.getPartidoById(id.toInt()).toPartido()
    }


}