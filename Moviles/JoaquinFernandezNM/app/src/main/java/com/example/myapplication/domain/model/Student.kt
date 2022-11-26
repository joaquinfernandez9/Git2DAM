package com.example.myapplication.domain.model

data class Student(
    val id: Int,
    val name: String,
    val subjects: List<Subject>
)