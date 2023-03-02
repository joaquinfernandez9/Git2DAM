package com.example.examen.data.remote

import com.example.examen.data.model.EnfermedadesResponse
import com.example.examen.data.model.HospitalesResponse
import com.example.examen.data.model.PacienteResponse
import com.example.examen.domain.model.Enfermedades
import com.example.examen.domain.model.Hospital
import com.example.examen.domain.model.Paciente
import com.example.examen.network.services.HospitalService
import com.example.examen.utils.NetworkResult
import javax.inject.Inject

class HospitalRemoteDataSouce @Inject constructor(
    private val service: HospitalService,
) : BaseApiResponse() {
    suspend fun fetchHospitales(): NetworkResult<List<Hospital>> {
        return safeApiCall(apiCall = {
            service.getHospitales()
        })
    }


    suspend fun fetchPacientes(): NetworkResult<List<Paciente>> {
        return safeApiCall(apiCall = {
            service.getPacientes()
        })
    }

    suspend fun updatePaciente(id: String, paciente: Paciente): NetworkResult<PacienteResponse> {
        return safeApiCall(apiCall = {service.updatePacientes(id,paciente)})
    }

    suspend fun postEnfermedad(id:String, enfermedades: Enfermedades): NetworkResult<EnfermedadesResponse>{
        return safeApiCall(apiCall = {service.postEnfermedades(id,enfermedades)})
    }

    suspend fun deleteHospital(id:String): NetworkResult<HospitalesResponse>{
        return safeApiCall(apiCall = {service.deleteHospital(id)})
    }
}