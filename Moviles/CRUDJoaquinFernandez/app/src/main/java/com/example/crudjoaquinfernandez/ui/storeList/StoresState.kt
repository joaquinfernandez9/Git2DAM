package com.example.crudjoaquinfernandez.ui.storeList

import com.example.crudjoaquinfernandez.domain.model.Store

data class StoresState(
    val list: List<Store> = emptyList(),
    val error: String? = null
)