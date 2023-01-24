package com.example.flowsjoaquinfernandez.data.local.repos

import com.example.flowsjoaquinfernandez.data.local.MovieDao
import com.example.flowsjoaquinfernandez.data.model.MovieDesc
import com.example.flowsjoaquinfernandez.data.model.toMovie
import com.example.flowsjoaquinfernandez.data.model.toMovieEntity
import com.example.flowsjoaquinfernandez.data.remote.MovieRemoteDS
import com.example.flowsjoaquinfernandez.domain.modelo.Movie
import com.example.flowsjoaquinfernandez.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject


//Repository which fetches data from Remote or Local data sources
class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val remote: MovieRemoteDS,
) {

    fun fetchTrendingMovies(): Flow<NetworkResult<List<Movie>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remote.fetchTrendingMovies()
            emit(result)
            if (result is NetworkResult.Success){
                result.data?.let { item ->
                    movieDao.deleteAll(item.map { it.toMovieEntity() })
                    movieDao.insertAll(item.map { it.toMovieEntity() })
                }
            } else {
                emit(fetchTrendingMoviesCached())
            }
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun fetchTrendingMoviesCached(): NetworkResult<List<Movie>> =
        movieDao.getAll().let {list->
            NetworkResult.Success(list.map { it.toMovie() })
        }


}