package com.example.flowsjoaquinfernandez.data.model

data class SerieDesc (
    val id: Int,
    val name: String,
    val overview: String,
    val original_name: String,
    val poster_path: String,
    val genres: List<GenreSingleSerie>
        )

data class GenreSingleSerie(val id: Int, val name: String)