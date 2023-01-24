package com.example.flowsjoaquinfernandez.utils

object Constants {
    const val GET_ALL_MOVIES_POP = "SELECT * FROM movie order by popularity DESC"
    const val GET_ALL_FROM_SERIE = "SELECT * FROM serie"
    const val GET_BY_NAME = "SELECT * FROM serie WHERE name Like :name"
    const val TMDB = "tmdb"
    const val ERROR = "Error"
    const val NO_CONNECTION = "No hay conexión a internet"
    const val PATH_MOVIES_GET_POPULAR = "/3/movie/popular"
    const val PATH_GET_MOVIES = "/3/movie/{movie_id}"
    const val MOVIE_ID  = "movie_id"
    const val PATH_SERIES_GET_POPULAR = "/3/tv/popular"
    const val PATH_SEARCH_SERIES = "/3/search/tv"
    const val QUERY = "query"
    const val API_KEY = "api_key"
}