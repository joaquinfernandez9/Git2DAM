package com.example.mundialjoaquinfernandez.ui.pantallas.listaJugadores

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialjoaquinfernandez.model.Jugador
import com.example.mundialjoaquinfernandez.ui.pantallas.listaEquipos.ListaEquiposState
import com.example.mundialjoaquinfernandez.usecases.jugadores.DeleteJugador
import com.example.mundialjoaquinfernandez.usecases.jugadores.GetAllJugadores
import com.example.mundialjoaquinfernandez.usecases.jugadores.InsertJugador
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaJugadoresViewModel @Inject constructor(
    private val getAllJugadores: GetAllJugadores,
    private val deleteJugador: DeleteJugador,
    private val insertJugador: InsertJugador
) : ViewModel() {
    private val _state = MutableLiveData<ListaJugadoresState>()
    val state: MutableLiveData<ListaJugadoresState> = _state


    fun handleEvent(event: ListaJugadoresEvent) {
        when (event) {
            is ListaJugadoresEvent.GetAll -> getAllJugadores(event.nombre)

            is ListaJugadoresEvent.DeleteJugador -> deleteJugador(event.nombre)

            is ListaJugadoresEvent.InsertJugador -> insertJugador(event.jugador, event.equipo)


        }
    }

    private fun getAllJugadores(nombreEquipo: String) {
        viewModelScope.launch {
            _state.value = ListaJugadoresState(list = getAllJugadores.invoke(nombreEquipo))
        }
    }

    private fun deleteJugador(nombre: String) {
        viewModelScope.launch {
            deleteJugador.invoke(nombre)
        }
    }

    fun insertJugador(jugador: Jugador, equipo: String) {
        viewModelScope.launch {
            insertJugador.invoke(jugador, equipo)
        }
    }

}