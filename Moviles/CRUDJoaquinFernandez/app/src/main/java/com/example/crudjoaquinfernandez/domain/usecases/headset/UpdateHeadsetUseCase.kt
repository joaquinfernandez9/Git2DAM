package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.HeadsetRepository
import com.example.crudjoaquinfernandez.data.modelo.toHeadsetEntity
import com.example.crudjoaquinfernandez.domain.model.Headset

class UpdateHeadsetUseCase(val repo: HeadsetRepository) {
    suspend operator fun invoke(headset: Headset) =
        repo.updateHeadset(headset.toHeadsetEntity())
}