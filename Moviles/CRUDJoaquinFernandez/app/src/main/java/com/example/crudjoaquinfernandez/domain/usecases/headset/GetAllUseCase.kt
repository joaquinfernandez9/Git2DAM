package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.HeadsetRepository

class GetAllUseCase(val repo: HeadsetRepository) {
    suspend operator fun invoke() = repo.getAllHeadsets()
}