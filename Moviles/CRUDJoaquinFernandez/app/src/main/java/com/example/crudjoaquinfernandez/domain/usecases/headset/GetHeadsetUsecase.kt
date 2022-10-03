package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset

class GetHeadsetUsecase {
    fun getHeadset(id: Int) = DataHeadset.getHeadset(id)
}