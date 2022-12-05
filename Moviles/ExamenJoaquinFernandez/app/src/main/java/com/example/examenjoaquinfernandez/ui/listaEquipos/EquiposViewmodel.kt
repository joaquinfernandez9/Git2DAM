package com.example.examenjoaquinfernandez.ui.listaEquipos

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenjoaquinfernandez.domain.usecases.equipo.DeleteUseCase
import com.example.examenjoaquinfernandez.domain.usecases.equipo.GetAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquiposViewmodel @Inject constructor(
    private val remove: DeleteUseCase,
    private val getAll: GetAllUseCase,
) : ViewModel(){

    private val _state = MutableLiveData<EquiposState>()
    val state: MutableLiveData<EquiposState> = _state

    fun handleEvent(event: EquiposEvent){
        when(event){
            is EquiposEvent.Delete -> {
                deleteEquipo(event.nombre)
            }
            EquiposEvent.GetAll ->{
                getAllEquipos()
            }
        }
    }

    private fun deleteEquipo(nombre: String){
        viewModelScope.launch {
            try {
                remove.invoke(nombre)
                _state.value = _state.value?.copy(list = getAll.invoke())
            } catch (e: Exception){
                _state.value = EquiposState(error = "Fallo en la base de datos")
            }
        }
    }

    private fun getAllEquipos(){
        viewModelScope.launch {
            _state.value = EquiposState(list = getAll.invoke())
        }
    }



}

