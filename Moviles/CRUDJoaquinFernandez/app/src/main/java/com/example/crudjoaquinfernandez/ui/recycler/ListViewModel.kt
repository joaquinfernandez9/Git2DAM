package com.example.crudjoaquinfernandez.ui.recycler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.usecases.headset.GetAllUseCase
import com.example.crudjoaquinfernandez.domain.usecases.headset.RemoveHeadsetUsecase
import timber.log.Timber

class RecyclerViewModel(
    private val removeHeadset: RemoveHeadsetUsecase,
    private val getAll: GetAllUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<ListState>()
    val state: MutableLiveData<ListState> = _state


    init {
        getAllHeadsets()
    }

    fun deleteHeadset(id: Int) {
        if (!removeHeadset.invoke(id)) {
            _state.value = ListState(
                error = "Error al eliminar el headset",
            )
            Timber.i("Error al eliminar")
        } else {
            _state.value = ListState(
                error = null,
                list = getAll.invoke()
            )
        }
    }


    private fun getAllHeadsets() {
        if (getAll.invoke().isEmpty()) {
            _state.value = ListState(
                error = "No hay headsets",
            )
            Timber.i("No hay headsets")
        } else {
            _state.value = ListState(
                list = getAll.invoke(),
            )
        }
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class RecyclerViewModelFactory(
    private val removeHeadset: RemoveHeadsetUsecase,
    private val getAll: GetAllUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecyclerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecyclerViewModel(
                removeHeadset,
                getAll,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

