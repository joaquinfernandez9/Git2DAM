package com.example.crudjoaquinfernandez.ui.mainScreen

import com.example.crudjoaquinfernandez.domain.model.Headset

sealed class MainEvent {
    class AddHeadset(val headset: Headset) : MainEvent()
    class RemoveHeadset(val id: Int) : MainEvent()
    class UpdateHeadset(val headset: Headset) : MainEvent()
    class GetHeadset(val id: Int) : MainEvent()
    object GetAll : MainEvent()
}