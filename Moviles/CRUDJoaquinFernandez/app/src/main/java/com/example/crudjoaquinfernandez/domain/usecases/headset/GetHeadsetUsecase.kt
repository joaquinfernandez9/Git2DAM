package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.HeadsetRepository
import javax.inject.Inject

class GetHeadsetUsecase @Inject constructor(private val repo: HeadsetRepository) {
    suspend operator fun invoke(id: Int) = repo.getHeadset(id)
}