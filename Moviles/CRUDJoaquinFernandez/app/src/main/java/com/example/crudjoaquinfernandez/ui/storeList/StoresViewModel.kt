package com.example.crudjoaquinfernandez.ui.storeList

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudjoaquinfernandez.domain.usecases.store.GetAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class StoresViewModel @Inject constructor(
    private val getAll: GetAllUseCase
) : ViewModel(){


    private val _state = MutableLiveData<StoresState>()
    val state : MutableLiveData<StoresState> = _state

    fun handleEvent(event: StoresEvent){
        when (event) {
            is StoresEvent.GetAll -> {
                getAll()
            }
        }
    }

    private fun getAll(){
        viewModelScope.launch {
            //no escribe la lista arriba
                _state.value = StoresState(
                    list = getAll.invoke(),
                    error = null
                )
        }
    }
}