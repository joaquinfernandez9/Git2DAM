package com.example.crudjoaquinfernandez.ui.mainScreen

import com.example.crudjoaquinfernandez.domain.model.Headset

data class MainState(
    val headset: Headset? = Headset(
        0, "null",
        mic = false, bluetooth = false
    ),
    val stringError: String? = null,
)

