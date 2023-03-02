package com.example.examen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examen.domain.model.Paciente
import java.util.UUID

@Entity(tableName = "pacientes")
data class PacienteEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "dni")
    val dni: String,
//    val enfermedades: List<String>
)