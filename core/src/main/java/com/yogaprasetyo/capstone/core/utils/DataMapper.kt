package com.yogaprasetyo.capstone.core.utils

import com.yogaprasetyo.capstone.core.data.source.local.entity.MovieEntity
import com.yogaprasetyo.capstone.core.data.source.remote.response.ResultsItem
import com.yogaprasetyo.capstone.core.domain.model.Movie

object DataMapper {

    private fun addBaseUrl(pathUrl: String): String = "$IMG_BASE_URL$pathUrl"

    fun mapResponseToEntities(input: List<ResultsItem>, type: String? = null): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                poster = addBaseUrl(it.posterPath),
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.voteAverage,
                ratingCount = it.voteCount,
                overview = it.overview,
                adult = it.adult,
                type = type,
                genres = it.genreIds.map { gId -> getGenreByKey(gId) }.joinToString(),
                backdrop = it.backdropPath?.let { path -> addBaseUrl(path) } ?: "",
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                poster = it.poster,
                backdrop = it.backdrop,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                ratingCount = it.ratingCount,
                genres = it.genres,
                overview = it.overview,
                adult = it.adult,
                type = it.type,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntities(input: Movie) = MovieEntity(
        movieId = input.movieId,
        poster = input.poster,
        backdrop = input.backdrop,
        title = input.title,
        releaseDate = input.releaseDate,
        rating = input.rating,
        ratingCount = input.ratingCount,
        genres = input.genres,
        overview = input.overview,
        adult = input.adult,
        type = input.type,
        isFavorite = input.isFavorite
    )
}