package com.yogaprasetyo.capstone.emovie.core.utils

import com.yogaprasetyo.capstone.emovie.core.BuildConfig

const val API_KEY = "ac992c711fd901227c04a53fc868bd42"
const val QUERY_API_KEY = "api_key"
const val QUERY_KEY = "query"
const val GENRE_KEY = "with_genres"
const val HOSTNAME = "api.themoviedb.org"
const val BASE_URL = BuildConfig.BASE_URL
const val IMG_BASE_URL = BuildConfig.IMG_BASE_URL
const val CERTIFICATE_PINNING = BuildConfig.CERTIFICATE_PINNING
val SECRET_PASSPHRASE = BuildConfig.SECRET_PASSPHRASE.toCharArray()

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