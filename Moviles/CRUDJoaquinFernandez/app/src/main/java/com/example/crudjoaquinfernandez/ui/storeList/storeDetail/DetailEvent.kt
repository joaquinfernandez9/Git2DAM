package com.example.crudjoaquinfernandez.ui.storeList.storeDetail

import com.example.crudjoaquinfernandez.domain.model.Store

sealed class DetailEvent {
    class AddStore(val store: Store) : DetailEvent()
    class DeleteHeadset(val id: Int) : DetailEvent()
    object GetAllHeadsets : DetailEvent()
}