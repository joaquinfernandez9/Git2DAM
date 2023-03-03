package com.example.examendiputadosjoaquinfernandez.data.local.repos

import com.example.examendiputadosjoaquinfernandez.data.local.dao.PartidoDao
import com.example.examendiputadosjoaquinfernandez.data.model.toDiputado
import com.example.examendiputadosjoaquinfernandez.data.model.toDiputadoEntity
import com.example.examendiputadosjoaquinfernandez.data.remote.RemoteDataSource
import com.example.examendiputadosjoaquinfernandez.domain.model.Diputado
import com.example.examendiputadosjoaquinfernandez.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DiputadoRepository @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val dao: PartidoDao

) {
    fun listDiputados(id:String): Flow<NetworkResult<List<Diputado>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = dataSource.getDiputados(id)
            if (result is NetworkResult.Success) {
                result.data.let { item ->
                    if (item != null) {
                        dao.deleteAll(item.map { it.toDiputadoEntity() })
                        dao.insertAll(item.map { it.toDiputadoEntity() })
                    }
                }
                emit(result)
            } else {
                emit(fetchDiputados())
            }
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun fetchDiputados(): NetworkResult<List<Diputado>> =
        dao.getAll().let { list ->
            NetworkResult.Success(list.map { it.toDiputado() })
        }

    fun listDiputados(): Flow<NetworkResult<List<Diputado>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = dataSource.getDiputados()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}