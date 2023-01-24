package com.example.flowsjoaquinfernandez.data.local.repos

import com.example.flowsjoaquinfernandez.data.local.SeriesDao
import com.example.flowsjoaquinfernandez.data.model.SerieDesc
import com.example.flowsjoaquinfernandez.data.model.toSerie
import com.example.flowsjoaquinfernandez.data.model.toSerieEntity
import com.example.flowsjoaquinfernandez.data.remote.SeriesRemoteDS
import com.example.flowsjoaquinfernandez.domain.modelo.Series
import com.example.flowsjoaquinfernandez.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepository @Inject constructor(
    private val dao: SeriesDao,
    private val remote: SeriesRemoteDS
) {

    fun fetchTrendingSeries(): Flow<NetworkResult<List<Series>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remote.fetchTrendingSeries()
            emit(result)
            if (result is NetworkResult.Success) {
                result.data?.let { item ->
                    dao.deleteAll(item.map { it.toSerieEntity() })
                    dao.insertAll(item.map { it.toSerieEntity() })
                }
            } else {
                emit(fetchTrendingSeriesCached())
            }
        }.flowOn(Dispatchers.IO)
    }
    private suspend fun fetchTrendingSeriesCached(): NetworkResult<List<Series>> =
        dao.getAll().let { list ->
            NetworkResult.Success(list.map { it.toSerie() })
        }

    fun fetchSerieByName(name: String): Flow<NetworkResult<List<Series>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remote.fetchSerieByName(name)
            emit(result)
            if (result is NetworkResult.Success) {
                result.data?.let { item ->
                    dao.deleteAll(item.map { it.toSerieEntity() })
                    dao.insertAll(item.map { it.toSerieEntity() })
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}