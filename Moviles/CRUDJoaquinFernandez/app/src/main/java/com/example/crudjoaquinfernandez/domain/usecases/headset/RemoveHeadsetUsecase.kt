package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.HeadsetRepository

class RemoveHeadsetUsecase(val repo: HeadsetRepository) {
    suspend operator fun invoke(id: Int) =
        repo.removeHeadset(id)
}