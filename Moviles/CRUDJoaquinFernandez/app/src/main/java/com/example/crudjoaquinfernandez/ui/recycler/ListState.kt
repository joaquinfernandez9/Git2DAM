package com.example.crudjoaquinfernandez.ui.recycler

import com.example.crudjoaquinfernandez.domain.model.Headset


data class ListState(
    val list : List<Headset> = emptyList(),
    val error: String? = null) {}