package com.example.flowsjoaquinfernandez.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flowsjoaquinfernandez.Config
import com.example.flowsjoaquinfernandez.domain.modelo.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val overview: String? = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
)

fun MovieEntity.toMovie(): Movie = Movie(
    id = id,
    titulo = title,
    overview = overview,
    popularity = popularity,
    poster_path = Config.IMAGE_URL+poster_path
)

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
    id = id,
    title = titulo,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path
)