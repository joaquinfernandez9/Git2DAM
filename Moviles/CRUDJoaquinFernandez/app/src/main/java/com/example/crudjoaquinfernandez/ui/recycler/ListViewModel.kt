package com.example.crudjoaquinfernandez.ui.recycler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.crudjoaquinfernandez.domain.usecases.headset.GetAllUseCase
import com.example.crudjoaquinfernandez.domain.usecases.headset.RemoveHeadsetUsecase
import com.example.crudjoaquinfernandez.ui.mainScreen.Const
import kotlinx.coroutines.launch
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

    fun handleEvent(event: RecyclerEvent) {
        when (event) {
            is RecyclerEvent.DeleteHeadset -> {
                deleteHeadset(event.id)
            }
            RecyclerEvent.GetAll -> {
                getAllHeadsets()
            }
        }
    }

    private fun deleteHeadset(id: Int) {
        viewModelScope.launch {
            try {
                removeHeadset(id)
                _state.value = ListState(getAll.invoke())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }

    private fun getAllHeadsets() {
        //haciendo el debug esto se llama 3 veces antes de funcionar
        viewModelScope.launch {
            _state.value = ListState(
                error = null,
                list = getAll.invoke()
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
            @Suppress(Const.uncheckedCast)
            return RecyclerViewModel(
                removeHeadset,
                getAll,
            ) as T
        }
        throw IllegalArgumentException(Const.unknownvmc)
    }

}

