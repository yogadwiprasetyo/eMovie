package com.yogaprasetyo.capstone.core.utils

const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMG_BASE_URL = "https://image.tmdb.org/t/p/original"
const val API_KEY = "ac992c711fd901227c04a53fc868bd42"
const val QUERY_API_KEY = "api_key"
const val QUERY_KEY = "query"
const val GENRE_KEY = "with_genres"

enum class TypeMovie {
    TRENDING, POPULAR, NOW_PLAYING, TOP_RATED
}

val genres = mapOf(
    28 to "Action",
    12 to "Adventure",
    35 to "Comedy",
    80 to "Crime",
    18 to "Drama",
    10751 to "Family",
    27 to "Horror",
    10749 to "Romance",
    878 to "Sci-fi",
    53 to "Thriller"
)