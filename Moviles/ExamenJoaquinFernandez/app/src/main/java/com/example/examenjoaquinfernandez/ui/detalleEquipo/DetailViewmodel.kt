package com.example.examenjoaquinfernandez.ui.detalleEquipo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenjoaquinfernandez.domain.usecases.componente.GetAllUseCase
import com.example.examenjoaquinfernandez.domain.usecases.equipo.AddUseCase
import com.example.examenjoaquinfernandez.domain.usecases.equipo.GetUseCase
import com.example.examenjoaquinfernandez.ui.addEquipo.AddState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class DetailViewmodel @Inject constructor(
    private val getAllComponents: GetAllUseCase,
    private val getEquipo: GetUseCase
) : ViewModel() {

    private val _state = MutableLiveData<DetailState>()
    val state: LiveData<DetailState> get() = _state

    fun handleEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetEquipo -> {
                event.nombre?.let { getEquipo(it) }
            }
            is DetailEvent.GetAllComponents -> {
//                event.nombre?.let { getAllComponents(it) }
            }
        }
    }

    private fun getEquipo(nombre: String) {
        viewModelScope.launch {
            try {
                val equipo = getEquipo.invoke(nombre)
                _state.value = DetailState(equipo = equipo)
            } catch (e: Exception) {
                _state.value = DetailState(error = "No se ha encontrado ningun equipo")
            }
        }
    }

    private fun getAllComponents(nombre: String) {
        viewModelScope.launch {
            try {
                _state.value?.equipo ?: getAllComponents.invoke(nombre)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }
}