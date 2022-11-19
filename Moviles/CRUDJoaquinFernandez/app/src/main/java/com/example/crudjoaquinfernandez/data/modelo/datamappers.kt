package com.example.crudjoaquinfernandez.data.modelo

import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.model.Model

fun HeadsetEntity.toHeadset(): Headset {
    return Headset(
        this.id, this.nombre, this.mic, this.bluetooth, emptyList()
    )
}

fun Headset.toHeadsetEntity(): HeadsetEntity =
    HeadsetEntity(this.id, this.bluetooth, this.name, this.mic)

fun ModelEntity.toModel(): Model = Model(this.idModel, this.model, this.headsetId)

fun Model.toModelEntity(): ModelEntity =
    ModelEntity(this.idModel, this.modelName, this.idHeadset)



