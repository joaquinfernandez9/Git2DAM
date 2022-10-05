package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudjoaquinfernandez.R
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.AddHeadsetUsecase
import com.example.crudjoaquinfernandez.domain.usecases.headset.GetHeadsetUsecase
import com.example.crudjoaquinfernandez.domain.usecases.headset.RemoveHeadsetUsecase
import com.example.crudjoaquinfernandez.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val addHeadset: AddHeadsetUsecase,
    private val removeHeadset: RemoveHeadsetUsecase,
    private val getHeadset: GetHeadsetUsecase,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    fun addHeadset(headset: Headset) {
        if (!addHeadset.invoke(headset)){
            _uiState.value = MainState(
                stringError = stringProvider.getString(R.string.errorAddHeadset)
            )
            _uiState.value = _uiState.value?.copy(stringError = null)
        }
    }

    fun removeHeadset(headset: Headset) {
        removeHeadset.removeHeadset(headset.id)
        _uiState.value = MainState(
            headset = Headset(
                id = 0,
                name = stringProvider.getString(R.string.name),
                mic = false,
                bluetooth = false,
            ), stringError = null
        )
    }

    fun getHeadset(headset: Headset) {
        _uiState.value = MainState(
            headset = getHeadset.getHeadset(headset.id),
            stringError = null,
        )
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val stringProvider: StringProvider,
    private val addHeadset: AddHeadsetUsecase,
    private val getHeadset: GetHeadsetUsecase,
    private val removeHeadset: RemoveHeadsetUsecase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                addHeadset,
                removeHeadset,
                getHeadset,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

