package com.example.examen.framework.xml.initScreen

import com.example.examen.framework.xml.initScreen.InitContract.*
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.data.local.repos.HospitalRepository
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
    private val repo: HospitalRepository,
) : ViewModel() {
    private val _hospitales: MutableStateFlow<State> by lazy { MutableStateFlow(State()) }
    val state: StateFlow<State> = _hospitales

//    private val _uiError = Channel<String>()
//    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: Event) {
        when (event) {
            Event.Cargar -> cargar()
        }
    }


    private fun cargar() {
        _hospitales.value = _hospitales.value.copy(isLoading = true)
        viewModelScope.launch {
            repo.listHospitales().collect { result ->
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
}