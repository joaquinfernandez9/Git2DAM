package com.example.crudjoaquinfernandez.domain.usecases.store

import com.example.crudjoaquinfernandez.data.StoreRepository
import javax.inject.Inject

class GetAllUseCase @Inject constructor(private val repo: StoreRepository) {
    suspend fun invoke() =
        repo.getAllStores()
}