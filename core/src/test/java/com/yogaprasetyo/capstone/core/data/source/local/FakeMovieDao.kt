package com.yogaprasetyo.capstone.core.data.source.local

import com.yogaprasetyo.capstone.core.data.source.local.entity.MovieEntity
import com.yogaprasetyo.capstone.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMovieDao : MovieDao {

    private var movieData = mutableListOf<MovieEntity>()

    override suspend fun insertMovie(movies: List<MovieEntity>) {
        movieData.addAll(movies)
    }

    override suspend fun updateFavoriteMovie(movie: MovieEntity) {
        movieData.find { it.movieId == movie.movieId }?.isFavorite = movie.isFavorite
    }

    override fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return flowOf(movieData.filter { it.isFavorite })
    }

    override fun getMoviesByType(type: String): Flow<List<MovieEntity>> {
        return flowOf(movieData.filter { it.type == type })
    }
}