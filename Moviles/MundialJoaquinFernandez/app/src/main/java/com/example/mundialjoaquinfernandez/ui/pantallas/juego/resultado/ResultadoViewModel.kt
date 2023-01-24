package com.example.mundialjoaquinfernandez.ui.pantallas.juego.resultado

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialjoaquinfernandez.model.Partido
import com.example.mundialjoaquinfernandez.usecases.partido.GetPartido
import com.example.mundialjoaquinfernandez.usecases.partido.InsertPartido
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultadoViewModel @Inject constructor(
    private val insertPartido: InsertPartido,
    private val getPartido: GetPartido

): ViewModel() {

    private val _state = MutableLiveData<ResultadoState>()
    val state: MutableLiveData<ResultadoState> = _state

    fun handleEvent(event: ResultadoEvent){
        when(event){
            is ResultadoEvent.JugarPartido -> {
                jugarPartido(event.partido)
            }
            is ResultadoEvent.GetPartido -> {
                getPartido(event.nombreLocal, event.nombreVisitante)
            }

        }
    }

    private fun jugarPartido(partido: Partido){
        viewModelScope.launch {
            insertPartido.invoke(partido)
        }
    }

    private fun getPartido(nombreLocal: String, nombreVisitante: String){
        viewModelScope.launch {
            state.value = ResultadoState(getPartido.invoke(nombreLocal, nombreVisitante))
        }
    }



}
