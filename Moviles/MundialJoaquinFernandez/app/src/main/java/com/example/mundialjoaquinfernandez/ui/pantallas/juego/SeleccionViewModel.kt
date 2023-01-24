package com.example.mundialjoaquinfernandez.ui.pantallas.juego

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialjoaquinfernandez.usecases.equipos.GetAllEquipos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeleccionViewModel @Inject constructor(
    private val getAllEquipos: GetAllEquipos
): ViewModel(){

    private val _state = MutableLiveData<SeleccionState>()
    val state: MutableLiveData<SeleccionState> = _state

    fun handleEvent(event: SeleccionEvent){
        when(event){
            is SeleccionEvent.GetAll -> {
                getAllEquipos()
            }
        }
    }

    private fun getAllEquipos(){
        viewModelScope.launch {
            _state.value = SeleccionState(list = getAllEquipos.invoke())
        }

    }





}