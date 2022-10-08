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

    fun addHeadset(headset: Headset) {
        if (!addHeadset.invoke(headset)){
            _uiState.value = MainState(
                stringError = null,
            )
            _uiState.value = _uiState.value?.copy(stringError = Const.s)
        }
    }

    fun removeHeadset(id: Int) {
        removeHeadset.removeHeadset(id)

    }

    fun get(id: Int): Headset {
        return getHeadset.getHeadset(id)
    }

    fun getAll(): List<Headset> {
        return allUseCase.invoke()
    }



    fun updateHeadset(id: Int, name: String, mic: Boolean, bluetooth: Boolean) {
        updateHeadsetUseCase.updateHeadset(id, name, mic, bluetooth)
    }

    fun showError(){
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

