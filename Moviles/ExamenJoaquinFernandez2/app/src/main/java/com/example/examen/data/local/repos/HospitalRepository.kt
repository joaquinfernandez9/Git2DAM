package com.example.examen.data.local.repos

import com.example.examen.data.model.HospitalesResponse
import com.example.examen.data.remote.HospitalRemoteDataSouce
import com.example.examen.domain.model.Hospital
import com.example.examen.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HospitalRepository @Inject constructor(
//    private val dao: HospitalesDao,
    private val hospitalRemoteDataSouce: HospitalRemoteDataSouce,
) {
    fun listHospitales(): Flow<NetworkResult<List<Hospital>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = hospitalRemoteDataSouce.fetchHospitales()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun deleteHospital(id: String): Flow<NetworkResult<HospitalesResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = hospitalRemoteDataSouce.deleteHospital(id)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}