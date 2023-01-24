package com.example.flowsjoaquinfernandez.data.remote

import com.example.flowsjoaquinfernandez.data.model.MovieDesc
import com.example.flowsjoaquinfernandez.data.model.toMovie
import com.example.flowsjoaquinfernandez.domain.modelo.Movie
import com.example.flowsjoaquinfernandez.network.services.MovieService
import com.example.flowsjoaquinfernandez.utils.NetworkResult
import javax.inject.Inject

class MovieRemoteDS @Inject constructor(
    private val movieService: MovieService
) : BaseApiResponse() {

    suspend fun fetchTrendingMovies(): NetworkResult<List<Movie>> {
        return safeApiCall (apiCall = {
                movieService.getPopularMovies() },
            transform = { trendingMovieResponse ->
                trendingMovieResponse
                    .results?.map { movieEntity -> movieEntity.toMovie() } ?: emptyList()
            })
    }

    suspend fun fetchMovie(id: Int): NetworkResult<MovieDesc> {
        return safeApiCall(apiCall = { movieService.getMovie(id) })
    }

}

