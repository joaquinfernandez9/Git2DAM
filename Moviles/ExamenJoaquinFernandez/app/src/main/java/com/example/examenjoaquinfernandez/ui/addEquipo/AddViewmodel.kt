package com.example.examenjoaquinfernandez.ui.addEquipo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenjoaquinfernandez.domain.model.Equipo
import com.example.examenjoaquinfernandez.domain.usecases.componente.GetAllUseCase
import com.example.examenjoaquinfernandez.domain.usecases.equipo.AddUseCase
import com.example.examenjoaquinfernandez.domain.usecases.equipo.GetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewmodel @Inject constructor(
    private val addEquipo: AddUseCase,
    private val getAllComponents: GetAllUseCase,
    private val getEquipo: GetUseCase
): ViewModel(){

    private val _state = MutableLiveData<AddState>()
    val state: LiveData<AddState> get() = _state

    fun handleEvent(event: AddEvent){
        when(event){
            is AddEvent.AddEquipo -> {
                addEquipo(event.equipo)
            }
            is AddEvent.GetEquipo -> {
                getEquipo(event.nombre)
            }
        }
    }


    private fun addEquipo(equipo: Equipo){
        viewModelScope.launch {
            try {
                addEquipo.invoke(equipo)
                _state.value = _state.value?.copy(error = "Todo correcto")
            } catch (e: Exception){
                _state.value = _state.value?.copy(error = "Error al añadir")
            }
        }
    }

    private fun getEquipo(nombre: String){
        viewModelScope.launch {
            try {
                val equipo  =getEquipo.invoke(nombre)
                equipo.componentes = getAllComponents.invoke(nombre)
                _state.value = _state.value?.copy(equipo = equipo)
            } catch (e: Exception){
                _state.value = AddState(error = "No se ha encontrado ningun equipo")
            }
        }
    }
}