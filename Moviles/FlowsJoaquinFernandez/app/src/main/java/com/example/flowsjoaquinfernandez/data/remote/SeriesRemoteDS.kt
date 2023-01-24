package com.example.flowsjoaquinfernandez.data.remote

import com.example.flowsjoaquinfernandez.data.model.SerieDesc
import com.example.flowsjoaquinfernandez.data.model.toSerie
import com.example.flowsjoaquinfernandez.domain.modelo.Series
import com.example.flowsjoaquinfernandez.network.services.SeriesService
import com.example.flowsjoaquinfernandez.utils.NetworkResult
import javax.inject.Inject

class SeriesRemoteDS @Inject constructor(
    private val service: SeriesService
) : BaseApiResponse() {

    suspend fun fetchTrendingSeries(): NetworkResult<List<Series>> {
        return safeApiCall(apiCall = {
            service.getPopularSeries()
        },
            transform = { trendingSerieResponse ->
                trendingSerieResponse
                    .results?.map { serieEntity -> serieEntity.toSerie() }
                    ?: emptyList()
            })

    }

    suspend fun fetchSerieByName(name: String): NetworkResult<List<Series>> {
        return safeApiCall(apiCall = { service.getSerieByName(name) },
            transform = { trendingSerieResponse ->
                trendingSerieResponse
                    .results?.map { serieEntity -> serieEntity.toSerie() }
                    ?: emptyList()
            })


    }


}