package com.example.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.StudentDao
import com.example.myapplication.data.dao.SubjectDao
import com.example.myapplication.data.model.StudentEntity
import com.example.myapplication.data.model.SubjectEntity

@Database(
    entities = [
        StudentEntity::class,
        SubjectEntity::class
    ], version = 1, exportSchema = true
)
abstract class Database: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao

}