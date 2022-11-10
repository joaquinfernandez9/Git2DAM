package com.example.crudjoaquinfernandez.ui.recycler

sealed class RecyclerEvent {
    class DeleteHeadset(val id: Int) : RecyclerEvent()
    object GetAll : RecyclerEvent()
}