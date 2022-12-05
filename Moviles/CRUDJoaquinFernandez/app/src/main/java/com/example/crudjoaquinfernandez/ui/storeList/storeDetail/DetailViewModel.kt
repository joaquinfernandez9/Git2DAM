package com.example.crudjoaquinfernandez.ui.storeList.storeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudjoaquinfernandez.domain.model.Store
import com.example.crudjoaquinfernandez.domain.usecases.headset.GetAllUseCase
import com.example.crudjoaquinfernandez.domain.usecases.headset.RemoveHeadsetUsecase
import com.example.crudjoaquinfernandez.domain.usecases.store.AddStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addStore: AddStoreUseCase,
    private val removeHeadset: RemoveHeadsetUsecase,
    private val getAllHeadset: GetAllUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<DetailState>()
    val uiState: LiveData<DetailState> get() = _uiState


    fun handleEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.AddStore -> {
                addStore(event.store)
            }
            is DetailEvent.DeleteHeadset -> {
                deleteHeadset(event.id)
            }
            is DetailEvent.GetAllHeadsets -> {
                getAllHeadsets()
            }
        }
    }


    private fun addStore(store: Store) {
        viewModelScope.launch {
            try {
                addStore.invoke(store)

            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = "No añadido")
            }
        }
    }

    private fun deleteHeadset(id: Int) {
        viewModelScope.launch {
            try {
                removeHeadset(id)
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = "No eliminado")
            }
        }
    }

    private fun getAllHeadsets() {
        viewModelScope.launch {
                getAllHeadset.invoke()
        }
    }


}