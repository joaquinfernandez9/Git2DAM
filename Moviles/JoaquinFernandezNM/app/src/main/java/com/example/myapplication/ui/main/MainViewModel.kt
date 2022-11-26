package com.example.crudjoaquinfernandez.ui.mainScreen

import androidx.lifecycle.*
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.model.Model
import com.example.crudjoaquinfernandez.domain.usecases.headset.*
import com.example.crudjoaquinfernandez.domain.usecases.model.AddModelUseCase
import com.example.crudjoaquinfernandez.domain.usecases.model.DeleteModelUseCase
import com.example.crudjoaquinfernandez.domain.usecases.model.GetAllModelsUseCase
import com.example.crudjoaquinfernandez.domain.usecases.model.GetModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addHeadset: AddHeadsetUsecase,
    private val removeHeadset: RemoveHeadsetUsecase,
    private val updateHeadsetUseCase: UpdateHeadsetUseCase,
    private val getHeadset: GetHeadsetUsecase,
    private val allUseCase: GetAllUseCase,
    private val deleteModel: DeleteModelUseCase,
    private val addModel: AddModelUseCase,
    private val getAllModels: GetAllModelsUseCase,
    private val getModel: GetModelUseCase,
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
                remove(event.id)
            }
            is MainEvent.UpdateHeadset -> {
                updateHeadset(event.headset)
            }
            is MainEvent.DeleteModel -> {
                deleteModel(event.id)
            }
            is MainEvent.AddModel -> {
                addModel(event.model)
            }
            is MainEvent.GetAllModels -> {
                getAllModels(event.id)
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

    private fun remove(id: Int) {
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
                headset.models = getAllModels.invoke(id)
                _uiState.value = MainState(
                    headset = headset,
                )
            } catch (e: Exception) {
                _uiState.value = MainState(
                    stringError = e.message,
                )
            }

        }
    }

    private fun getAll() {
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

    private fun deleteModel(id: Int) {
        viewModelScope.launch {
            try {
                val model = getModel.invoke(id)
                deleteModel.invoke(model)
                val headset = getHeadset.invoke(model.idHeadset)
                headset.models  = getAllModels.invoke(headset.id)
                _uiState.value = MainState(
                    headset,
                    stringError = null,
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(stringError = e.message)
            }
        }
    }

    private fun addModel(model: Model) {
        viewModelScope.launch {
            try {
                addModel.invoke(model)
                val headset = getHeadset.invoke(model.idHeadset)
                headset.models  = getAllModels.invoke(headset.id)
                _uiState.value = MainState(
                    headset,
                    null,
                )
//                _uiState.value = _uiState.value?.copy(stringError = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(stringError = e.message)
            }
        }
    }

    private fun getAllModels(idHeadset: Int) {
        viewModelScope.launch {
            try {
                _uiState.value?.headset ?: getAllModels.invoke(idHeadset)
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(stringError = e.message)
            }
        }
    }


}





