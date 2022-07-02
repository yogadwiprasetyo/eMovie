package com.yogaprasetyo.capstone.emovie.core.data.source.local.room

import androidx.room.*
import com.yogaprasetyo.capstone.emovie.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    suspend fun updateFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE type = :type")
    fun getMoviesByType(type: String): Flow<List<MovieEntity>>
}