package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset
import com.example.crudjoaquinfernandez.data.HeadsetRepository

class GetAllUseCase(val repo: HeadsetRepository) {
    operator fun invoke() = repo.getAllHeadsets()
}