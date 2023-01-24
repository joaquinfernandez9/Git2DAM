package com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialjoaquinfernandez.usecases.equipos.GetAllEquipos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaEquiposViewModel @Inject constructor(
    private val getAllEquipos: GetAllEquipos
) : ViewModel() {

    private val _state = MutableLiveData<ListaEquiposState>()
    val state: MutableLiveData<ListaEquiposState> = _state

    fun handleEvent(event: ListaEquiposEvent){
        when(event){
            is ListaEquiposEvent.GetAll -> {
                getAllEquipos()
            }
        }
    }

    private fun getAllEquipos(){
        viewModelScope.launch {
            _state.value = ListaEquiposState(list = getAllEquipos.invoke())
        }
    }




}