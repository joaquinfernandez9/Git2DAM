package com.example.crudjoaquinfernandez.ui.mainScreen

import com.example.crudjoaquinfernandez.domain.model.Headset

data class MainState
    (
    val headset: Headset = Headset(
        0, "null",
        0.0, mic = false, bluetooth = false
    ),
    val error: String? = null,
)

