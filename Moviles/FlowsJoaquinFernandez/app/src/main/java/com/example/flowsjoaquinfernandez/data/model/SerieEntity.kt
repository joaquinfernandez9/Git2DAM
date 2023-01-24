package com.example.flowsjoaquinfernandez.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flowsjoaquinfernandez.domain.modelo.Series

@Entity(tableName = "serie")
data class SerieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val original_name: String,
    val poster_path: String,
    val popularity: Double,
)
fun SerieEntity.toSerie(): Series {
    return Series(
        id = id,
        name = name,
        originalName = original_name,
        posterPath = poster_path,
        popularity = popularity,
    )
}

fun Series.toSerieEntity(): SerieEntity {
    return SerieEntity(
        id = id,
        name = name,
        original_name = originalName,
        poster_path = posterPath,
        popularity = popularity,
    )
}
