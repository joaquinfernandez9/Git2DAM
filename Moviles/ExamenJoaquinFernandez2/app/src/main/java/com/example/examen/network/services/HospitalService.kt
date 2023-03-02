package com.example.examen.network.services

import androidx.room.Delete
import com.example.examen.data.model.EnfermedadesResponse
import com.example.examen.data.model.HospitalesResponse
import com.example.examen.data.model.PacienteResponse
import com.example.examen.domain.model.Enfermedades
import com.example.examen.domain.model.Hospital
import com.example.examen.domain.model.Paciente
import com.example.examen.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HospitalService {
    @GET("/hospitales")
    suspend fun getHospitales(): Response<List<Hospital>>

    @GET("/pacientes")
    suspend fun getPacientes(): Response<List<Paciente>>


    @PUT("/pacientes/{id}")
    suspend fun updatePacientes(
        @Path("id") id: String,
        @Body pacientes: Paciente
    ): Response<PacienteResponse>

    @POST("/pacientes/{id}")
    suspend fun postEnfermedades(
        @Path("id") id: String,
        @Body enfermedades: Enfermedades
    ): Response<EnfermedadesResponse>


    @DELETE("/hospitales/{id}")
    suspend fun deleteHospital(@Path("id") id: String): Response<HospitalesResponse>
}