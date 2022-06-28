package com.yogaprasetyo.capstone.core.data

import com.yogaprasetyo.capstone.core.data.source.local.LocalDataSource
import com.yogaprasetyo.capstone.core.data.source.remote.RemoteDataSource
import com.yogaprasetyo.capstone.core.data.source.remote.network.ApiResponse
import com.yogaprasetyo.capstone.core.data.source.remote.response.ResponseListMovie
import com.yogaprasetyo.capstone.core.domain.model.Movie
import com.yogaprasetyo.capstone.core.domain.repository.IMovieRepository
import com.yogaprasetyo.capstone.core.utils.DataMapper
import com.yogaprasetyo.capstone.core.utils.GENRE_KEY
import com.yogaprasetyo.capstone.core.utils.QUERY_KEY
import com.yogaprasetyo.capstone.core.utils.TypeMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {

    override fun searchMovies(queries: MutableMap<String, String>): Flow<Resource<List<Movie>>> {
        val query = queries[QUERY_KEY] as String
        return object :
            NetworkBoundResource<List<Movie>, ResponseListMovie>() {
            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<ResponseListMovie>> =
                remoteDataSource.searchMovies(queries)

            override suspend fun saveCallResult(data: ResponseListMovie) {
                val movieList = DataMapper.mapResponseToEntities(data.results, query)
                localDataSource.insertMovie(movieList)
            }

            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMoviesByType(query)
                    .map { movieList -> DataMapper.mapEntitiesToDomain(movieList) }
        }.asFlow()
    }


    override fun getMoviesByGenre(queries: MutableMap<String, String>): Flow<Resource<List<Movie>>> {
        val genre = queries[GENRE_KEY] as String
        return object :
            NetworkBoundResource<List<Movie>, ResponseListMovie>() {
            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<ResponseListMovie>> =
                remoteDataSource.discoverMovies(queries)

            override suspend fun saveCallResult(data: ResponseListMovie) {
                val movieList = DataMapper.mapResponseToEntities(data.results, genre)
                localDataSource.insertMovie(movieList)
            }

            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMoviesByType(genre)
                    .map { movieList -> DataMapper.mapEntitiesToDomain(movieList) }
        }.asFlow()
    }

    override fun getMoviesByType(
        type: TypeMovie,
        queries: MutableMap<String, String>
    ): Flow<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, ResponseListMovie>() {
            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<ResponseListMovie>> =
                when (type) {
                    TypeMovie.TRENDING -> remoteDataSource.getTrendingMovies()
                    TypeMovie.NOW_PLAYING -> remoteDataSource.getNowPlayingMovies(queries)
                    TypeMovie.POPULAR -> remoteDataSource.getPopularMovies(queries)
                    TypeMovie.TOP_RATED -> remoteDataSource.getTopRatedMovies(queries)
                }

            override suspend fun saveCallResult(data: ResponseListMovie) {
                val movieList = DataMapper.mapResponseToEntities(data.results, type.name)
                localDataSource.insertMovie(movieList)
            }

            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMoviesByType(type.name)
                    .map { movieList -> DataMapper.mapEntitiesToDomain(movieList) }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        localDataSource.setFavoriteMovie(movieEntity, newState)
    }
}