package com.example.flowsjoaquinfernandez.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composecositas.domain.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val Name: String,
)

fun MovieEntity.toMovie(): Movie = Movie(
    id = id,
    titulo = Name,
)

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
    id = id,
    Name = titulo,
)