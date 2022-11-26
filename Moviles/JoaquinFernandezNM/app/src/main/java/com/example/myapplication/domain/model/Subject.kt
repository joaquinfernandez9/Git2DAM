package com.example.myapplication.domain.model

data class Subject(
    val id: Int,
    val name: String,
    val students: List<Student>
)
