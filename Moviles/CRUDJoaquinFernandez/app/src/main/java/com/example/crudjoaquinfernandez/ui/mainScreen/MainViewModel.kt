package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.AddHeadsetUsecase
import com.example.crudjoaquinfernandez.domain.usecases.headset.GetHeadsetUsecase
import com.example.crudjoaquinfernandez.domain.usecases.headset.RemoveHeadsetUsecase
import com.example.crudjoaquinfernandez.domain.usecases.headset.UpdateHeadsetUsecase

class MainViewModel(
    private val addHeadset: AddHeadsetUsecase,
    private val removeHeadset: RemoveHeadsetUsecase,
    private val updateHeadset: UpdateHeadsetUsecase,
    private val getHeadset: GetHeadsetUsecase,
) : ViewModel() {
    private val _uiState = MutableLiveData<MainState>()

    fun addHeadset(headset: Headset) {
        addHeadset.addHeadset(headset)
        _uiState.value = MainState(
            headset = Headset(
                id = 0,
                name = "",
                price = 0.0,
                mic = false,
                bluetooth = false,
            ),
            error = null,
        )
    }

    fun removeHeadset(headset: Headset) {
        removeHeadset.removeHeadset(headset.id)
        _uiState.value = MainState(
            headset = Headset(
                id = 0,
                name = "",
                price = 0.0,
                mic = false,
                bluetooth = false,
            ),
            error = null,
        )
    }

    fun updateHeadset(headset: Headset) {
        updateHeadset.updateHeadset(headset)
        _uiState.value = MainState(
            headset = Headset(
                id = 0,
                name = "",
                price = 0.0,
                mic = false,
                bluetooth = false,
            ),
            error = null,
        )
    }

    fun getHeadset(headset: Headset) {
        _uiState.value = getHeadset.getHeadset(headset.id)?.let {
            MainState(
                headset = it,
                error = null,
            )
        }
    }

}

