package com.example.examen.data.remote

import com.example.examen.domain.model.Hospital
import com.example.examen.network.services.HospitalService
import com.example.examen.utils.NetworkResult
import javax.inject.Inject

class HospitalRemoteDataSouce @Inject constructor(
    private val service: HospitalService,
): BaseApiResponse() {
    suspend fun fetchHospitales(): NetworkResult<List<Hospital>> {
        return safeApiCall (apiCall = {
            service.getHospitales()
        })
    }
}