package com.yogaprasetyo.capstone.emovie.core.data.source.local

import com.yogaprasetyo.capstone.emovie.core.data.source.local.entity.MovieEntity
import com.yogaprasetyo.capstone.emovie.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getMoviesByType(type: String): Flow<List<MovieEntity>> = movieDao.getMoviesByType(type)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovie(movies)

    suspend fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}