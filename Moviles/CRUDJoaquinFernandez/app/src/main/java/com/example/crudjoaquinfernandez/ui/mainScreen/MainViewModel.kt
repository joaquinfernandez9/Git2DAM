package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.*


class MainViewModel(
    private val addHeadset: AddHeadsetUsecase,
    private val removeHeadset: RemoveHeadsetUsecase,
    private val updateHeadsetUseCase: UpdateHeadsetUseCase,
    private val getHeadset: GetHeadsetUsecase,
    private val allUseCase: GetAllUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState


    fun handleEvent(event: MainEvent){
        when(event){
            MainEvent.GetAll -> {
                allUseCase.invoke()
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
                updateHeadsetUseCase(event.headset)
            }
        }
    }

    private fun addHeadset(headset: Headset) {

        if (!addHeadset.invoke(headset)) {
            _uiState.value = MainState(
                stringError = "error al añadir el headset",
            )
        } else {
            _uiState.value = MainState(
                headset = headset,
                stringError = null,
            )
        }
    }

    private fun removeHeadset(id: Int) {
        if (!removeHeadset.invoke(id)) {
            _uiState.value = MainState(
                stringError = "error al eliminar el headset",
            )
        } else {
            _uiState.value = MainState(
                stringError = null,
            )
        }
    }



    private fun get(id: Int) {
        val headset = getHeadset.invoke(id)
        _uiState.value = MainState(
            headset = headset,
            stringError = null,
        )
    }


    private fun updateHeadset(headset: Headset) {
        updateHeadsetUseCase.invoke(headset)
        _uiState.value = MainState(
            headset = headset,
            stringError = null,
        )
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

