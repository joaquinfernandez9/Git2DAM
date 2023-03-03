package com.example.examendiputadosjoaquinfernandez.data.local.repos

import com.example.examendiputadosjoaquinfernandez.data.remote.RemoteDataSource
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido
import com.example.examendiputadosjoaquinfernandez.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PartidoRepository @Inject constructor(
    private val dataSource: RemoteDataSource,
) {
    fun listPartidos() : Flow<NetworkResult<List<Partido>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = dataSource.fetchPartidos()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun deletePartido(id:String): Flow<NetworkResult<Partido>>{
        return flow {
            emit(NetworkResult.Loading())
            var result = dataSource.deletePartido(id)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun updatePartido(partido: Partido): Flow<NetworkResult<Partido>>{
        return flow {
            emit(NetworkResult.Loading())
            var result = dataSource.updatePartido(partido)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun postPartido(partido: Partido): Flow<NetworkResult<Partido>>{
        return flow {
            emit(NetworkResult.Loading())
            var result = dataSource.postPartido(partido)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}