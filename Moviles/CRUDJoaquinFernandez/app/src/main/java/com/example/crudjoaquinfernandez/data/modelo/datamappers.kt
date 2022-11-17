package com.example.crudjoaquinfernandez.data.modelo

import com.example.crudjoaquinfernandez.domain.model.Headset
import com.example.crudjoaquinfernandez.domain.model.Model

fun HeadsetEntity.toHeadset(): Headset {
    return Headset(
        this.id,
        this.nombre,
        this.mic,
        this.bluetooth,
        null
    )
}

fun Headset.toHeadsetEntity(): HeadsetEntity =
    HeadsetEntity(this.id, this.bluetooth, this.name, this.mic)

fun ModelEntity.toModel(): Model =
    Model(this.id, this.model)

fun Model.toModelEntity(): ModelEntity =
    ModelEntity(this.id_model, this.model)

fun HeadsetModel.toHeadset(): Headset =
    Headset(
        this.headset.id,
        this.headset.nombre,
        this.headset.mic,
        this.headset.bluetooth,
        this.mic?.map { it.toModel() },
    )

fun Headset.toHeadsetModel(): HeadsetModel =
    HeadsetModel(this.toHeadsetEntity(),
        this.models?.map { it.toModelEntity() }
    )
