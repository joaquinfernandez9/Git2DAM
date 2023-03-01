package com.example.joaquinfernandezcomposefinal.ui.framework.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joaquinfernandezcomposefinal.domain.model.Equipo
import com.example.joaquinfernandezcomposefinal.domain.usecases.GetAllTeams
import com.example.joaquinfernandezcomposefinal.domain.usecases.PlayMatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val play: PlayMatch,
    private val getAll: GetAllTeams

) : ViewModel(){
    private val _state = MutableStateFlow<List<Equipo>>(emptyList())
    val state: StateFlow<List<Equipo>> = _state

     fun handleEvent(event: GameContract.Event){
        when(event){
            is GameContract.Event.GetAll -> getAll()
            is GameContract.Event.Play -> play(event.local, event.visitante)
        }
    }

    private fun play(local: String, visitante: String){
        viewModelScope.launch {
            play.invoke(local, visitante)
        }
    }

    init {
        getAll()
    }
    private fun getAll() {
        viewModelScope.launch {
            _state.value = getAll.invoke()
        }
    }
}