package com.example.examen.framework.compose.litsHospitals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.domain.model.Hospital
import com.example.examen.domain.usecases.GetHospitales
import com.example.examen.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalsViewModel @Inject constructor(
    private val getAll: GetHospitales
) : ViewModel() {
    private val _hospitales: MutableStateFlow<HospitalsContract.State> by lazy {
        MutableStateFlow(HospitalsContract.State())
    }
    val hospitales: StateFlow<HospitalsContract.State> = _hospitales

//    private val _uiError = Channel<String>()
//    val uiError = _uiError.receiveAsFlow()

    init {
        getAllHospitales()
    }

    private fun getAllHospitales() {
        _hospitales.value = _hospitales.value.copy(isLoading = true)
        viewModelScope.launch {
            getAll.invoke().collect { result ->
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