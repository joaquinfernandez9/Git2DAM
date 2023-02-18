package com.example.composecositas.domain.usecases

import com.example.composecositas.data.repos.MovieRepository
import com.example.composecositas.domain.model.Movie
import javax.inject.Inject

class GetAllMovies @Inject constructor(private val repo: MovieRepository) {
    suspend fun invoke() = repo.getAll()
}

class InsertMovie @Inject constructor(private val repo: MovieRepository) {
    suspend fun invoke(movie: Movie) = repo.insert(movie)
}

class DeleteMovie @Inject constructor(private val repo: MovieRepository) {
    suspend fun invoke(movie: Movie) = repo.delete(movie)
}
