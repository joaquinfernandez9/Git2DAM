package com.example.composecositas.data.repos

import com.example.composecositas.data.local.MovieDao
import com.example.composecositas.domain.model.Movie
import com.example.flowsjoaquinfernandez.data.model.toMovie
import com.example.flowsjoaquinfernandez.data.model.toMovieEntity
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
) {

    suspend fun getAll() = movieDao.getAll().map { it.toMovie() }

    suspend fun insert(movie: Movie) = movieDao.insert(movie.toMovieEntity())

    suspend fun delete(movie: Movie) = movieDao.delete(movie.toMovieEntity())

}