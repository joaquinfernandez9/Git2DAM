package com.example.crudjoaquinfernandez.ui.mainScreen

import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.model.Model

sealed class MainEvent {
    class AddHeadset(val headset: Headset) : MainEvent()
    class RemoveHeadset(val id: Int) : MainEvent()
    class UpdateHeadset(val headset: Headset) : MainEvent()
    class GetHeadset(val id: Int) : MainEvent()
    object GetAll : MainEvent()
    class DeleteModel(val id: Int) : MainEvent()
    class AddModel(val model: Model) : MainEvent()
    class GetAllModels(val id: Int) : MainEvent()
}