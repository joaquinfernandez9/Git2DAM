package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset

class GetHeadsetUsecase {
    operator fun invoke(id: Int) = DataHeadset.getHeadset(id)
}