package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.HeadsetRepository

class RemoveHeadsetUsecase(private val repo: HeadsetRepository) {
    suspend operator fun invoke(id: Int) =
        repo.removeHeadset(id)
}