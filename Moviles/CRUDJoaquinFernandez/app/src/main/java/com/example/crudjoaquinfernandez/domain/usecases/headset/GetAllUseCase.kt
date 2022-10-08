package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset

class GetAllUseCase {
    operator fun invoke() = DataHeadset.getAllHeadsets()
}