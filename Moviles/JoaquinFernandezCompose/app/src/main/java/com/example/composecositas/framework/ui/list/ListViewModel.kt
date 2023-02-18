package com.example.composecositas.framework.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecositas.domain.model.Movie
import com.example.composecositas.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAll: GetAllMovies,
    private val delete: DeleteMovie
) : ViewModel(){

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun handleEvent(event: ListContract.Event){
        when(event){
            is ListContract.Event.GetAll -> getAllMovies()
            is ListContract.Event.Delete -> deleteMovie(event.movie)
        }
    }

    init {
        getAllMovies()
    }

    private fun getAllMovies(){
        viewModelScope.launch { _movies.value = getAll.invoke() }
    }

    private fun deleteMovie(movie: Movie){
        viewModelScope.launch { delete.invoke(movie) }
    }


}