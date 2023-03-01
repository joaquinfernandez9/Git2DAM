package com.example.examen.data.local.repos

import com.example.examen.data.local.dao.HospitalesDao
import com.example.examen.data.model.toHospitalEntity
import com.example.examen.domain.model.Hospital
import com.example.examen.network.services.HospitalService
import com.example.examen.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HospitalRepository @Inject constructor(
    private val dao: HospitalesDao,
    private val service: HospitalService,
) {
    fun fetchHospitales(): Flow<NetworkResult<List<Hospital>>> {
        return flow {
            val result = service.getHospitales()
            emit(result)
            if (result is NetworkResult.Success){
                result.data?.let { item ->
                    dao.deleteAll(item.map { it.toHospitalEntity() })
                    dao.insertAll(item.map { it.toHospitalEntity() })
                }
            }
        }
    }
}