package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.HeadsetRepository
import com.example.crudjoaquinfernandez.data.modelo.toHeadsetEntity
import com.example.crudjoaquinfernandez.domain.model.Headset
import javax.inject.Inject

class AddHeadsetUsecase @Inject constructor(private val repo: HeadsetRepository) {
    suspend fun invoke(headset: Headset) =
        repo.addHeadset(headset.toHeadsetEntity())
}