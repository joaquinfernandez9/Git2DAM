package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset
import com.example.crudjoaquinfernandez.domain.model.Headset

class RemoveHeadsetUsecase {
    fun removeHeadset(id: Int) = DataHeadset.removeHeadset(id)
}