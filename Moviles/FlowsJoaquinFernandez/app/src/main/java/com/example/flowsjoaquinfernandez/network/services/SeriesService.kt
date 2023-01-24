package com.example.flowsjoaquinfernandez.network.services

import com.example.flowsjoaquinfernandez.data.model.SerieDesc
import com.example.flowsjoaquinfernandez.data.model.TrendingResponseSeries
import com.example.flowsjoaquinfernandez.utils.Constants
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesService {
    @GET(Constants.PATH_SERIES_GET_POPULAR)
    suspend fun getPopularSeries(): Response<TrendingResponseSeries>

    @GET(Constants.PATH_SEARCH_SERIES)
    suspend fun getSerieByName(@Query(Constants.QUERY) nombre: String): Response<TrendingResponseSeries>
}