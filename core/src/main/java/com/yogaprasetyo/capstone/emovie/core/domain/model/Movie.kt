package com.yogaprasetyo.capstone.emovie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val poster: String,
    val backdrop: String,
    val title: String,
    val releaseDate: String?,
    val rating: Double,
    val ratingCount: Int,
    val genres: String,
    val overview: String,
    val adult: Boolean,
    val type: String? = null,
    val isFavorite: Boolean = false
) : Parcelable
