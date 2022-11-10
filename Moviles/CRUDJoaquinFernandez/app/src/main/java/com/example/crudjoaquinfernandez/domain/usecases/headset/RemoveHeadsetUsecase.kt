package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset
import com.example.crudjoaquinfernandez.data.HeadsetRepository
import com.example.crudjoaquinfernandez.domain.model.Headset

class RemoveHeadsetUsecase(val repo: HeadsetRepository) {
    operator fun invoke(id: Int) =
        repo.removeHeadset(id)
}