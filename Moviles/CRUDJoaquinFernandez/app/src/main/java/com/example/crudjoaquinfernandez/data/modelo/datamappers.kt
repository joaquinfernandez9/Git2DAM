package com.example.crudjoaquinfernandez.data.modelo

import com.example.crudjoaquinfernandez.domain.model.Headset

fun HeadsetEntity.toHeadset(): Headset {
    return Headset(this.id, this.nombre, this.mic, this.bluetooth)
}

fun Headset.toHeadsetEntity(): HeadsetEntity =
    HeadsetEntity(this.id, this.name, this.mic, this.bluetooth)