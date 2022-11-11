package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.lifecycle.*
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.*
import kotlinx.coroutines.launch
import timber.log.Timber


class MainViewModel(
    private val addHeadset: AddHeadsetUsecase,
    private val removeHeadset: RemoveHeadsetUsecase,
    private val updateHeadsetUseCase: UpdateHeadsetUseCase,
    private val getHeadset: GetHeadsetUsecase,
    private val allUseCase: GetAllUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState


    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.GetAll -> {
                getAll()
            }
            is MainEvent.GetHeadset -> {
                get(event.id)
            }
            is MainEvent.AddHeadset -> {
                addHeadset(event.headset)
            }
            is MainEvent.RemoveHeadset -> {
                removeHeadset(event.id)
            }
            is MainEvent.UpdateHeadset -> {
                updateHeadset(event.headset)
            }
        }
    }

    private fun addHeadset(headset: Headset) {
        viewModelScope.launch {
            try {
                addHeadset.invoke(headset)
                _uiState.value = _uiState.value?.copy(stringError = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(stringError = e.message)
            }
        }
    }

    private fun removeHeadset(id: Int) {
        viewModelScope.launch {
            try {
                removeHeadset(id)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }


    private fun get(id: Int) {
        viewModelScope.launch {
            try {
                val headset = getHeadset.invoke(id)
                _uiState.value = MainState(
                    headset = headset,
                    stringError = null,
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(stringError = e.message)
            }

        }
    }

    private fun getAll(){
        viewModelScope.launch {
            allUseCase.invoke()
        }
    }


    private fun updateHeadset(headset: Headset) {
        viewModelScope.launch {
            try {
                updateHeadsetUseCase.invoke(headset)
                _uiState.value = _uiState.value?.copy(stringError = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(stringError = e.message)
            }
        }
    }

    fun showError() {
        _uiState.value = _uiState.value?.copy(stringError = null)
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val addHeadset: AddHeadsetUsecase,
    private val getHeadset: GetHeadsetUsecase,
    private val updateHeadsetUseCase: UpdateHeadsetUseCase,
    private val removeHeadset: RemoveHeadsetUsecase,
    private val allUseCase: GetAllUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addHeadset,
                removeHeadset,
                updateHeadsetUseCase,
                getHeadset,
                allUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

