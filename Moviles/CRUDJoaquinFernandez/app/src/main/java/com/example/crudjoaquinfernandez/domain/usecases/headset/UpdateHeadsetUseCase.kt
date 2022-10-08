package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset

class UpdateHeadsetUseCase {
    fun updateHeadset(id: Int, name: String, mic: Boolean, bluetooth: Boolean,) =
        DataHeadset.updateHeadset(id, name, mic, bluetooth)
}