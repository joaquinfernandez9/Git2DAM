package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset
import com.example.crudjoaquinfernandez.domain.model.Headset

class UpdateHeadsetUseCase {
    operator fun invoke(headset: Headset) =
        DataHeadset.updateHeadset(headset)
}