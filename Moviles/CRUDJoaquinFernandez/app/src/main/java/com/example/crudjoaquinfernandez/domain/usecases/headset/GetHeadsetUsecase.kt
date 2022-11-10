package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset
import com.example.crudjoaquinfernandez.data.HeadsetRepository

class GetHeadsetUsecase(val repo: HeadsetRepository) {
    operator fun invoke(id: Int) = repo.getHeadset(id)
}