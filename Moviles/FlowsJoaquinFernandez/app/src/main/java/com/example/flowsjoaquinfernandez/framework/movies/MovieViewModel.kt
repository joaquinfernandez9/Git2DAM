package com.example.flowsjoaquinfernandez.framework.movies

import com.example.flowsjoaquinfernandez.data.local.repos.MovieRepository
import com.example.flowsjoaquinfernandez.framework.movies.MovieContract.*

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowsjoaquinfernandez.utils.Constants
import com.example.flowsjoaquinfernandez.utils.NetworkResult
import com.example.flowsjoaquinfernandez.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repo: MovieRepository
) : ViewModel(){


    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()


    fun handleEvent(event: Event){
        when(event){
            Event.Cargar -> cargar()
        }
    }


    private fun cargar(){
        viewModelScope.launch {
            if (Utils.hasInternetConnection(context = context)){
                repo.fetchTrendingMovies()
                    .catch(action = {
                        cause -> _uiError.send(cause.message ?: Constants.ERROR)
                    })
                    .collect{
                        result ->
                        when(result) {
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        isLoading = false
                                    )
                                }
                                _uiError.send(result.message ?: Constants.ERROR)
                            }
                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    movies = result.data ?: emptyList(), isLoading = false
                                )
                            }
                        }
                    }
            } else {
                _uiState.update {
                    it.copy(
                        error = Constants.NO_CONNECTION,
                        isLoading = false
                    )
                }
            }
        }
    }
}