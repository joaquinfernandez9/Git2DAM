package com.example.crudjoaquinfernandez.domain.usecases.store

import com.example.crudjoaquinfernandez.data.StoreRepository
import com.example.crudjoaquinfernandez.data.modelo.datamappers.toStoreEntity
import com.example.crudjoaquinfernandez.domain.model.Store
import javax.inject.Inject

class AddStoreUseCase @Inject constructor(private val repo: StoreRepository) {
    suspend fun invoke(store: Store) =
        repo.insertStore(store.toStoreEntity())

}