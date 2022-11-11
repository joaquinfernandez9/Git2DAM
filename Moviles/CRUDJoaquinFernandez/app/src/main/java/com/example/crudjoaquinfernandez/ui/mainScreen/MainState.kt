package com.example.crudjoaquinfernandez.ui.mainScreen

import com.example.crudjoaquinfernandez.domain.model.Headset

data class MainState(
    val headset: Headset? = Headset(
        0, "error", 0, 0,
    ),
    val stringError: String? = null,
)

