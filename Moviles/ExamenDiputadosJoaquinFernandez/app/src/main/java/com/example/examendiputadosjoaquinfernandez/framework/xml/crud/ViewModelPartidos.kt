package com.example.examendiputadosjoaquinfernandez.framework.xml.crud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido
import com.example.examendiputadosjoaquinfernandez.domain.usecases.DeletePartido
import com.example.examendiputadosjoaquinfernandez.domain.usecases.GetPartidos
import com.example.examendiputadosjoaquinfernandez.domain.usecases.PostPartido
import com.example.examendiputadosjoaquinfernandez.domain.usecases.UpdatePartido
import com.example.examendiputadosjoaquinfernandez.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewModelPartidos @Inject constructor(
    private val getPartidos: GetPartidos,
    private val postPartido: PostPartido,
    private val deletePartido: DeletePartido,
    private val updatePartido: UpdatePartido,
) : ViewModel() {

    private val _partidos: MutableStateFlow<Contract.State> by lazy {
        MutableStateFlow(Contract.State())
    }
    val state: StateFlow<Contract.State> = _partidos

    fun handleEvent(event: Contract.Event) {
        when (event) {
            is Contract.Event.Cargar -> cargar()
            is Contract.Event.PostPartido -> add(event.partido)
            is Contract.Event.DeletePartido -> borrar(event.id)
            is Contract.Event.UpdatePartido -> updateName(event.id, event.nombre)
        }
    }

    init {
        cargar()
    }

    private fun cargar() {
        _partidos.value = _partidos.value.copy(isLoading = true)
        viewModelScope.launch {
            getPartidos.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _partidos.update {
                            it.copy(
                                error = result.message,
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _partidos.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _partidos.update {
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

    //cambiar nombre
    private fun updateName(nombre: String, id: String) {
        viewModelScope.launch {
            val partido = Partido(UUID.fromString(nombre), id)
            updatePartido.invoke(partido).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _partidos.update {
                            it.copy(
                                error = result.message,
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _partidos.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _partidos.update {
                            it.copy(
                                error = "Partido actualizado",
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }

    //borrar
    private fun borrar(id: String) {
        viewModelScope.launch {
            deletePartido.invoke(id).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _partidos.update {
                            it.copy(
                                error = result.message,
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _partidos.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _partidos.update {
                            it.copy(
                                error = "Partido borrado",
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }

    //añadir
    private fun add(nombre: String) {
        viewModelScope.launch {
            val partido = Partido(UUID.randomUUID(), nombre)
            postPartido.invoke(partido).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _partidos.update {
                            it.copy(
                                error = result.message,
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _partidos.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _partidos.update {
                            it.copy(
                                error = "Partido añadido",
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }

}