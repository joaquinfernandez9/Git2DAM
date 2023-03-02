package com.example.examen.data.local.repos

import com.example.examen.data.local.dao.HospitalesDao
import com.example.examen.data.local.dao.PacientesDao
import com.example.examen.data.model.EnfermedadesResponse
import com.example.examen.data.model.PacienteResponse
import com.example.examen.data.model.toPacientes
import com.example.examen.data.model.toPacientesEntity
import com.example.examen.data.remote.HospitalRemoteDataSouce
import com.example.examen.domain.model.Enfermedades
import com.example.examen.domain.model.Paciente
import com.example.examen.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PacientesRepository @Inject constructor(
    private val daoHospi: HospitalesDao,
    private val hospitalRDS: HospitalRemoteDataSouce,
    private val daoPacientes: PacientesDao,
) {

    fun listPacientes(): Flow<NetworkResult<List<Paciente>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = hospitalRDS.fetchPacientes()
            emit(result)
            if (result is NetworkResult.Success) {
                result.data.let { item ->
                    if (item != null) {
                        daoPacientes.deleteAll(item.map { it.toPacientesEntity() })
                        daoPacientes.insertAll(item.map { it.toPacientesEntity() })
                    }
                }
            } else {
                emit(fetchPacientes())
            }

        }.flowOn(Dispatchers.IO)
    }

    private suspend fun fetchPacientes(): NetworkResult<List<Paciente>> =
        daoPacientes.getAll().let { list ->
            NetworkResult.Success(list.map { it.toPacientes() })
        }


    fun updatePaciente(id:String, paciente: Paciente): Flow<NetworkResult<PacienteResponse>>{
        return flow {
            emit(NetworkResult.Loading())
            var result = hospitalRDS.updatePaciente(id,paciente)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun postEnfermedades(id:String, enfermedades: Enfermedades):Flow<NetworkResult<EnfermedadesResponse>>{
        return flow {
            emit(NetworkResult.Loading())
            var result = hospitalRDS.postEnfermedad(id,enfermedades)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


}