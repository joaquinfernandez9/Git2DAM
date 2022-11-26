package com.example.crudjoaquinfernandez.domain.model

data class Model(
    val idModel: Int,
    var modelName: String,
    val idHeadset: Int,
) {
    constructor(modelName: String, idHeadset: Int) : this(0, modelName, idHeadset)
}

