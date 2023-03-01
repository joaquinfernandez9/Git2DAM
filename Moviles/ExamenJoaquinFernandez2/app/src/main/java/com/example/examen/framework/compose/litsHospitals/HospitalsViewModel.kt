package com.example.examen.framework.compose.litsHospitals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.domain.model.Hospital
import com.example.examen.domain.usecases.GetHospitales
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalsViewModel @Inject constructor(
    private val getAll: GetHospitales
) : ViewModel() {
    private val _hospitales = MutableStateFlow<List<Hospital>>(emptyList())
    val hospitales: StateFlow<List<Hospital>> = _hospitales

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    init {
        getAllHospitales()
    }

    private fun getAllHospitales() {
        viewModelScope.launch {
            getAll.invoke().catch(
                action = { cause ->
                    _uiError.send(cause.message ?: "Error")
                }
            ).collect()
        }
    }
}