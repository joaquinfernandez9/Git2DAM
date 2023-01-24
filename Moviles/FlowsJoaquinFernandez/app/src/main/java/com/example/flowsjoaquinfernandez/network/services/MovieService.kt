package com.example.flowsjoaquinfernandez.network.services

import com.example.flowsjoaquinfernandez.data.model.MovieDesc
import com.example.flowsjoaquinfernandez.data.model.TrendingResponse
import com.example.flowsjoaquinfernandez.utils.Constants
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET(Constants.PATH_MOVIES_GET_POPULAR)
    suspend fun getPopularMovies(): Response<TrendingResponse>

    @GET(Constants.PATH_GET_MOVIES)
    suspend fun getMovie(@Path(Constants.MOVIE_ID) id: Int) : Response<MovieDesc>
}