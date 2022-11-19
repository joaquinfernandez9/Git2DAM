package com.example.crudjoaquinfernandez.domain.model

data class Headset(
    val id: Int,
    var name: String,
    var mic: Int,
    var bluetooth: Int,
    var models: List<Model>,
)