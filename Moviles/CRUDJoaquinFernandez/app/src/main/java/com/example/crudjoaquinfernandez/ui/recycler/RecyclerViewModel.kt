package com.example.crudjoaquinfernandez.ui.recycler

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudjoaquinfernandez.domain.usecases.headset.GetAllUseCase
import com.example.crudjoaquinfernandez.domain.usecases.headset.RemoveHeadsetUsecase

class RecyclerViewModel(
    private val removeHeadset: RemoveHeadsetUsecase,
//    private val getAll: GetAllUseCase,
) : ViewModel() {

    fun deleteHeadset(id: Int) {
        removeHeadset.removeHeadset(id)
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class RecyclerViewModelFactory(
    private val removeHeadset: RemoveHeadsetUsecase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecyclerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecyclerViewModel(removeHeadset) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

