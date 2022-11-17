package com.example.crudjoaquinfernandez.data

object ConstData {
    const val databaseName = "cascos_database"
    const val dataPath = "database/cascos.db"
    const val tableName = "cascos"
    const val bluetooth ="bluetooth"
    const val name ="name"
    const val mic ="mic"
    const val id = "id"

    //querys
    const val getAll = "SELECT * FROM cascos"
    const val getById = "SELECT * FROM cascos WHERE id = :id"
    const val delete = "DELETE FROM cascos WHERE id = :id"
}