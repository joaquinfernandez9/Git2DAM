package com.example.examen.framework.xml.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.domain.model.Enfermedades
import com.example.examen.domain.model.Paciente
import com.example.examen.domain.usecases.GetHospitales
import com.example.examen.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPacientesDet: GetHospitales
) : ViewModel() {
    private val _paciente: MutableStateFlow<DetailContract.State> by lazy {
        MutableStateFlow(DetailContract.State())
    }
    val paciente: StateFlow<DetailContract.State> = _paciente

    fun event(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.GetPaciente -> getPaciente(event.id)
            is DetailContract.Event.VerEnfermedades -> verEnfermedades(event.paciente)
            is DetailContract.Event.UpdatePaciente -> updatePaciente(event.id, event.paciente)
            is DetailContract.Event.PostEnfermedad -> addEnfermedad(event.id, event.enfermedades)
        }
    }

    private fun verEnfermedades(paciente: Paciente){
        _paciente.value = _paciente.value.copy(isLoading = true)
        viewModelScope.launch {
            getPacientesDet.getPacientes().collect {result ->
                when(result){
                    is NetworkResult.Error -> {
                        _paciente.update {
                            it.copy(
                                error = result.message,
                                isLoading = false
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _paciente.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _paciente.update {
                            it.copy(
                                enfermedades = paciente.enfermedades,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getPaciente(id: String) {
        viewModelScope.launch {
            getPacientesDet.getPacientes().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _paciente.update {
                            it.copy(
                                error = result.message,
                                isLoading = false

                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _paciente.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        val pacientes = result.data?.find { it.id == id }
                        if (pacientes != null) {
                            _paciente.update {
                                it.copy(
                                    pacientes = pacientes,
                                    isLoading = false

                                )
                            }
                        } else {
                            _paciente.update {
                                it.copy(
                                    error = "No se ha encontrado el paciente",
                                    isLoading = false

                                )
                            }
                        }
                    }
                }

            }
        }
    }

    private fun updatePaciente(id: String, paciente: Paciente) {
        viewModelScope.launch {
            getPacientesDet.updatePaciente(id, paciente).collect { result ->
                when(result) {
                    is NetworkResult.Error -> {
                        _paciente.update {
                            it.copy(
                                error = result.message,
                                isLoading = false
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _paciente.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _paciente.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun addEnfermedad(id: String, enfermedad: Enfermedades) {
        _paciente.value = _paciente.value.copy(isLoading = true)
        viewModelScope.launch {
            getPacientesDet.postEnfermedad(id, enfermedad).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _paciente.update {
                            it.copy(
                                error = result.message,
                                isLoading = false

                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _paciente.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _paciente.update {
                            it.copy(
                                isLoading = false

                            )
                        }
                    }
                }
            }
        }
    }


}