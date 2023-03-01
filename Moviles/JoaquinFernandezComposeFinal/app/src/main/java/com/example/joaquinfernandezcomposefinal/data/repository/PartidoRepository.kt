package com.example.mundialjoaquinfernandez.data.repository

import com.example.mundialjoaquinfernandez.data.dao.EquiposDao
import com.example.mundialjoaquinfernandez.data.dao.PartidoDao
import com.example.mundialjoaquinfernandez.data.model.PartidoConEquipos
import com.example.mundialjoaquinfernandez.data.model.PartidoEntity
import com.example.joaquinfernandezcomposefinal.data.model.datamapper.toPartido
import com.example.joaquinfernandezcomposefinal.domain.model.Partido
import java.time.LocalDate
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

    suspend fun getLastMatch(): Partido {
        val partido = partidoDao.getLastMatch()
        return partido.toPartido()
    }

    suspend fun getAllMatches(): List<Partido> {
        val partidos = partidoDao.getAllMatches()
        return partidos.map { it.toPartido() }
    }


}