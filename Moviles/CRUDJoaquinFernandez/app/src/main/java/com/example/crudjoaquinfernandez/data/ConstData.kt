package com.example.crudjoaquinfernandez.data

object ConstData {
    const val databaseName = "cascos_database"
    const val dataPath = "database/cascos.db"
    const val tableName = "cascos"
    const val bluetooth ="bluetooth"
    const val name ="name"
    const val mic ="mic"
    const val id = "id"
    const val headsetDB = "headsetDB"
    const val getHeadsetDB = "database/cascos.db"
    const val modelId = "modelId"
    const val headsetId = "headsetId"
    const val modelos = "modelos"
    const val idModel = "idModel"
    const val model = "model"
    //querys
    const val getAll = "SELECT * FROM cascos"
    const val getById = "SELECT * FROM cascos WHERE id = :id"
    const val delete = "DELETE FROM cascos WHERE id = :id"
    const val getAllModels = "SELECT * FROM modelos where headsetId = :id"
    const val getModel = "select * from modelos where idModel = :id"
}