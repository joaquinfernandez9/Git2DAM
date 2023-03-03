package com.example.examendiputadosjoaquinfernandez.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examendiputadosjoaquinfernandez.domain.model.Diputado
import java.util.*

@Entity(tableName = "diputados")
data class DiputadoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "fechaEntradaCongreso")
    val fecha_entrada: String,
    @ColumnInfo(name = "corrupto")
    val corrupto: Boolean,
    @ColumnInfo(name = "idPartido")
    val id_partido: UUID,
//    @ColumnInfo(name = "causasConfirmadas")
//    val causasConf: List<CausasConfirmadas>,
//    @ColumnInfo(name = "causasSupuestas")
//    val causasSupuestas: List<CausasSupuestas>,
)

fun DiputadoEntity.toDiputado(): Diputado {
    return Diputado(
        id = id,
        nombre = nombre,
        fechaEntradaCongreso = fecha_entrada,
        corrupto = corrupto,
        idPartido = id_partido,
        causasConfirmadas = listOf(),
        causasSupuestas = listOf(),
    )
}

fun Diputado.toDiputadoEntity(): DiputadoEntity {
    return DiputadoEntity(
        id = id,
        nombre = nombre,
        fecha_entrada = fechaEntradaCongreso,
        corrupto = corrupto,
        id_partido = idPartido,
    )
}