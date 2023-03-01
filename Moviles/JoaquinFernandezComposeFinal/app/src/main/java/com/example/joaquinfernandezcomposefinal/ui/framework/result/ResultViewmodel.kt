package com.example.joaquinfernandezcomposefinal.ui.framework.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joaquinfernandezcomposefinal.domain.model.Partido
import com.example.joaquinfernandezcomposefinal.domain.usecases.GetLastMatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewmodel @Inject constructor(
    private val getLast: GetLastMatch
) : ViewModel(){

    private val _state = MutableStateFlow(Partido())
    val state = _state

    fun handleEvent(event: ResultContract.Event){
        when(event){
            is ResultContract.Event.GetLast -> getLast()
        }
    }

//    init {
//        getLast()
//    }

    private fun getLast(){
        viewModelScope.launch {
            _state.value = getLast.invoke()
        }
    }
}