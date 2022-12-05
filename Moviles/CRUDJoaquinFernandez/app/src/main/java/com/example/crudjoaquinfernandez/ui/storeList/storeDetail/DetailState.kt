package com.example.crudjoaquinfernandez.ui.storeList.storeDetail

import com.example.crudjoaquinfernandez.domain.model.Store

data class DetailState (
    val store: Store,
    val error: String
)