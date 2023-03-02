package com.example.examen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examen.domain.model.Hospital
import com.example.examen.domain.model.Paciente
import java.util.UUID

@Entity(tableName = "hospitales")
data class HospitalEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: UUID,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "numeroCamas")
    val numeroCamas: Int,
    @ColumnInfo(name = "direccion")
    val direccion: String,
)

