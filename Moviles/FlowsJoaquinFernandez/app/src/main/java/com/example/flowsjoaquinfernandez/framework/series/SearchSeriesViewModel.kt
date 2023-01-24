package com.example.flowsjoaquinfernandez.framework.series

import com.example.flowsjoaquinfernandez.framework.series.SeriesContract.*
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowsjoaquinfernandez.data.local.repos.SeriesRepository
import com.example.flowsjoaquinfernandez.framework.movies.MovieContract
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
class SearchSeriesViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repo: SeriesRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: Event) {
        when (event) {
            Event.Cargar -> cargar()
            is Event.Buscar -> buscar(event.nombre)
        }
    }

    private fun buscar(nombre: String) {
        viewModelScope.launch {
            repo.fetchSerieByName(nombre).collect { result ->
                when (result) {
                    is NetworkResult.Success -> _uiState.update {
                        it.copy(
                            series = result.data ?: emptyList(), isLoading = false
                        )
                    }
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
                }
            }
        }
    }

        fun cargar() {
            viewModelScope.launch {
                if (Utils.hasInternetConnection(context = context)) {
                    repo.fetchTrendingSeries()
                        .catch(action = { cause ->
                            _uiError.send(cause.message ?: Constants.ERROR)
                        })
                        .collect { result ->
                            when (result) {
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
                                        series = result.data ?: emptyList(), isLoading = false
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