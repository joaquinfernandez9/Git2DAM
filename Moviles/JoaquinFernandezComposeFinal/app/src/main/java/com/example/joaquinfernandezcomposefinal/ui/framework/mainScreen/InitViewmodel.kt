package com.example.joaquinfernandezcomposefinal.ui.framework.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joaquinfernandezcomposefinal.domain.model.Equipo
import com.example.joaquinfernandezcomposefinal.domain.model.Partido
import com.example.joaquinfernandezcomposefinal.domain.usecases.GetAllMatches
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitViewmodel @Inject constructor(
    private val getAll: GetAllMatches
) : ViewModel() {
    private val _state = MutableStateFlow<List<Partido>>(emptyList())
    val state: StateFlow<List<Partido>> = _state

    fun handleEvent(event: InitContract.Event){
        when(event){
            is InitContract.Event.Init -> init()
        }
    }

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch {
            _state.value = getAll.invoke()
        }
    }
}