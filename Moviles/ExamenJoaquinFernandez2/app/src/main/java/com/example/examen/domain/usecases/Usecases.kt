package com.example.examen.domain.usecases

import com.example.examen.data.local.repos.HospitalRepository
import com.example.examen.data.local.repos.PacientesRepository
import com.example.examen.domain.model.Enfermedades
import com.example.examen.domain.model.Paciente
import javax.inject.Inject

class GetHospitales @Inject constructor(
    private val repo: HospitalRepository,
    private val pacientesRepo: PacientesRepository,
    ) {
    fun invoke() = repo.listHospitales()

    fun getPacientes() = pacientesRepo.listPacientes()

    fun updatePaciente(id: String, pacientes: Paciente) = pacientesRepo.updatePaciente(id, pacientes)

    fun deleteHospital(id: String) = repo.deleteHospital(id)

    fun postEnfermedad(id: String, enfermedades: Enfermedades) = pacientesRepo.postEnfermedades(id, enfermedades)
}