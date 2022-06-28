package com.yogaprasetyo.capstone.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    var movieId: Int,
    var poster: String,
    var backdrop: String,
    var title: String,
    var releaseDate: String,
    var rating: Double,
    var ratingCount: Int,
    var genres: String,
    var overview: String,
    var adult: Boolean,
    var type: String? = null,
    var isFavorite: Boolean = false
)