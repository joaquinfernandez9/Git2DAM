package com.example.examendiputadosjoaquinfernandez.framework.compose.diputados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examendiputadosjoaquinfernandez.domain.model.Diputado
import com.example.examendiputadosjoaquinfernandez.domain.usecases.GetDiputados
import com.example.examendiputadosjoaquinfernandez.domain.usecases.GetPartidos
import com.example.examendiputadosjoaquinfernandez.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewModelDiputados @Inject constructor(
    private val getDiputados: GetDiputados,
    private val getPartidos: GetPartidos
) : ViewModel() {
    private val _diputados: MutableStateFlow<DiputadosContract.State> by lazy {
        MutableStateFlow(DiputadosContract.State())
    }
    val state: StateFlow<DiputadosContract.State> = _diputados

    fun handleEvent(event: DiputadosContract.Event) {
        when (event) {
            is DiputadosContract.Event.Cargar -> cargar()
            is DiputadosContract.Event.LimpiarError -> limpiarError()
            is DiputadosContract.Event.GetDiputados -> getDiputados(event.id)
            is DiputadosContract.Event.GetDiputado -> getDiputado(event.dipu)

        }
    }
    init {
        cargar()
    }

    private fun cargar() {
        _diputados.value = _diputados.value.copy(isLoading = true)
        viewModelScope.launch {
            getPartidos.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _diputados.update {
                            it.copy(
                                error = result.message,
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _diputados.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _diputados.update {
                            it.copy(
                                partidos = result.data,
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }

    fun getDiputados(id: String) {
        _diputados.value = _diputados.value.copy(isLoading = true)
        viewModelScope.launch {
            getDiputados.invoke(id).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _diputados.update {
                            it.copy(
                                error = result.message,
                                isLoading = false
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _diputados.update {
                            it.copy(
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _diputados.update {
                            it.copy(
                                diputados = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

    fun limpiarError(){
        _diputados.update {
            it.copy(
                error = null
            )
        }
    }

    fun getDiputado(diputado: String) {
        viewModelScope.launch {
            getDiputados.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _diputados.update {
                            it.copy(
                                error = result.message,
                                isLoading = false
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _diputados.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        val dipu = result.data?.find { it.nombre == diputado }
                        if (dipu != null) {
                            _diputados.update {
                                it.copy(
                                    dipu = dipu,
                                    isLoading = false
                                )
                            }
                        } else {
                            _diputados.update {
                                it.copy(
                                    error = "No se ha encontrado el diputado",
                                    isLoading = false
                                )
                            }
                        }

                    }
                }
            }
        }


    }
}