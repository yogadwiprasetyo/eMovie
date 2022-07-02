package com.yogaprasetyo.capstone.emovie.core.domain.usecase

import com.yogaprasetyo.capstone.emovie.core.data.Resource
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.domain.repository.IMovieRepository
import com.yogaprasetyo.capstone.emovie.core.utils.TypeMovie
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun searchMovies(queries: MutableMap<String, String>): Flow<Resource<List<Movie>>> =
        movieRepository.searchMovies(queries)

    override fun getMoviesByGenre(queries: MutableMap<String, String>): Flow<Resource<List<Movie>>> =
        movieRepository.getMoviesByGenre(queries)

    override fun getMoviesByType(
        type: TypeMovie,
        queries: MutableMap<String, String>
    ): Flow<Resource<List<Movie>>> =
        movieRepository.getMoviesByType(type, queries)

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override suspend fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieRepository.setFavoriteMovie(movie, newState)

}