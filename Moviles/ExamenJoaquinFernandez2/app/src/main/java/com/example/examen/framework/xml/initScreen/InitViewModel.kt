package com.example.examen.framework.xml.initScreen

import com.example.examen.framework.xml.initScreen.InitContract.*
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.data.local.repos.HospitalRepository
import com.example.examen.domain.model.Hospital
import com.example.examen.domain.usecases.GetHospitales
//import com.example.examen.framework.compose.litsHospitals.HospitalsContract
import com.example.examen.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repo: GetHospitales,
) : ViewModel() {
    private val _hospitales: MutableStateFlow<State> by lazy { MutableStateFlow(State()) }
    val state: StateFlow<State> = _hospitales

//    private val _uiError = Channel<String>()
//    val uiError = _uiError.receiveAsFlow()


    fun handleEvent(event: Event) {
        when (event) {
            is Event.Cargar-> cargar()
            is Event.GetPacientes-> getPacientesHospital(event.hospital)
        }
    }


    private fun cargar() {
        _hospitales.value = _hospitales.value.copy(isLoading = true)
        viewModelScope.launch {
            repo.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _hospitales.update {
                            it.copy(
                                error = result.message,
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _hospitales.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _hospitales.update {
                            it.copy(
                                hospitales = result.data,
                                isLoading = false,
                            )
                        }
                    }

                }
            }
        }
    }

    //get pacientes de hospital
    private fun getPacientesHospital(hospital: Hospital) {
        _hospitales.value = _hospitales.value.copy(isLoading = true)
        viewModelScope.launch {
            repo.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _hospitales.update {
                            it.copy(
                                error = result.message,
                                isLoading = false,
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _hospitales.update {
                            it.copy(
                                isLoading = true,
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _hospitales.update {
                            it.copy(
                                pacientes = hospital.pacientes,
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }


    }
}