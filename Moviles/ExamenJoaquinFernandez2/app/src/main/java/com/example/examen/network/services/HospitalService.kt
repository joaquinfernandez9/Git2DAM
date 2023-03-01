package com.example.examen.network.services

import com.example.examen.domain.model.Hospital
import com.example.examen.utils.NetworkResult
import retrofit2.http.GET

interface HospitalService {
    @GET("hospitales")
    suspend fun getHospitales(): NetworkResult<List<Hospital>>
}