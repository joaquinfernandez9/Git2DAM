package com.example.crudjoaquinfernandez.ui.mainScreen

import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.model.Model


data class MainState(
    var headset: Headset? = null,
    val stringError: String? = null,
)

