package com.example.examen.framework.xml.getAllPatients

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.domain.usecases.GetHospitales
import com.example.examen.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(
    private val getPatients: GetHospitales
): ViewModel(){

    private val _pacientes: MutableStateFlow<PatientsContract.State> by lazy {
        MutableStateFlow(PatientsContract.State())
    }
    val patients: StateFlow<PatientsContract.State> = _pacientes


    fun handleEvent(event: PatientsContract.Event) {
        when(event){
            is PatientsContract.Event.Cargar -> cargar()
        }
    }
    init {
        cargar()
    }

    private fun cargar(){
        _pacientes.value = _pacientes.value.copy(isLoading = true)
        viewModelScope.launch {
            getPatients.getPacientes().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _pacientes.update {
                            it.copy(
                                error = result.message,
                                isLoading = false
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _pacientes.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _pacientes.update {
                            it.copy(
                                pacientes = result.data,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }



}