package com.example.myapplication.data.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.myapplication.data.model.StudentEntity
import com.example.myapplication.data.model.SubjectEntity

data class StudentWithSubject(
    @Embedded val student: StudentEntity,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<SubjectEntity>
)