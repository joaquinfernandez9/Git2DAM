package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.HeadsetRepository

class GetAllUseCase(private val repo: HeadsetRepository) {
    suspend operator fun invoke() = repo.getAllHeadsets()
}