package com.example.composecositas.framework.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecositas.domain.model.Movie
import com.example.composecositas.domain.usecases.InsertMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val add: InsertMovie
) : ViewModel() {
    private val _state = MutableStateFlow(AddContract.State())
    val state = _state.asStateFlow()

    fun handleEvent(event: AddContract.Event){
        when(event){
            is AddContract.Event.Add -> addMovie(event.movie)
        }
    }


    private fun addMovie(name: Movie){
        viewModelScope.launch { add.invoke(name) }
    }
}