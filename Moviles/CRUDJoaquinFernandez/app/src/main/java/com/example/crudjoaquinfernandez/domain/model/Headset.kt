package com.example.crudjoaquinfernandez.domain.model

data class Headset(
    val id: Int,
    val name: String,
    val price: Double,
    val mic: Boolean,
    val bluetooth: Boolean,
)