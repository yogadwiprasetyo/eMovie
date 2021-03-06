package com.yogaprasetyo.capstone.emovie.core.domain.repository

import com.yogaprasetyo.capstone.emovie.core.data.Resource
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.utils.TypeMovie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun searchMovies(queries: MutableMap<String, String>): Flow<Resource<List<Movie>>>
    fun getMoviesByGenre(queries: MutableMap<String, String>): Flow<Resource<List<Movie>>>
    fun getMoviesByType(
        type: TypeMovie,
        queries: MutableMap<String, String>
    ): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>
    suspend fun setFavoriteMovie(movie: Movie, newState: Boolean)
}