package com.yogaprasetyo.capstone.emovie.core.data.source.local

import com.yogaprasetyo.capstone.emovie.core.data.source.local.room.MovieDao
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyTrending
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.generateDummyListMovieEntity
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldEquals
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldFalse
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldNotNull
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldTrue
import com.yogaprasetyo.capstone.emovie.core.utils.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalDataSourceTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var movieDao: MovieDao
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        movieDao = FakeMovieDao()
        localDataSource = LocalDataSource(movieDao)
    }

    @Test
    fun `when getMoviesByType Should Not Null`() = runTest {
        val dummyMoviesEntity = generateDummyListMovieEntity()
        movieDao.insertMovie(dummyMoviesEntity)

        val expectedTrending = dummyMoviesEntity.filter { it.type == dummyTrending }
        val actualTrending = movieDao.getMoviesByType(dummyTrending).first()

        assertNotNull(shouldNotNull, actualTrending)
        assertEquals(shouldEquals, expectedTrending.size, actualTrending.size)
    }

    @Test
    fun `when setFavoriteMovie to True Should Exist in getFavoriteMovies`() = runTest {
        val dummyMoviesEntity = generateDummyListMovieEntity()
        val dummyMovieFavorite = dummyMoviesEntity.first { !it.isFavorite }
        dummyMovieFavorite.isFavorite = true

        movieDao.insertMovie(dummyMoviesEntity)
        movieDao.updateFavoriteMovie(dummyMovieFavorite)

        val actualFavoriteMovie = movieDao.getFavoriteMovies().first()
        assertTrue(shouldTrue, actualFavoriteMovie.contains(dummyMovieFavorite))
    }

    @Test
    fun `when setFavoriteMovie to False Should Not Exist in getFavoriteMovie`() = runTest {
        val dummyMoviesEntity = generateDummyListMovieEntity()
        val dummyMovieUnfavored = dummyMoviesEntity.first { it.isFavorite }
        dummyMovieUnfavored.isFavorite = false

        movieDao.insertMovie(dummyMoviesEntity)
        movieDao.updateFavoriteMovie(dummyMovieUnfavored)

        val actualFavoriteMovie = movieDao.getFavoriteMovies().first()
        assertFalse(shouldFalse, actualFavoriteMovie.contains(dummyMovieUnfavored))
    }

    @Test
    fun `when getFavoriteMovies Should Not Null`() = runTest {
        val dummyMoviesEntity = generateDummyListMovieEntity()
        movieDao.insertMovie(dummyMoviesEntity)

        val expectedFavorites = dummyMoviesEntity.filter { it.isFavorite }
        val actualFavorite = movieDao.getFavoriteMovies().first()

        assertNotNull(shouldNotNull, actualFavorite)
        assertEquals(shouldEquals, expectedFavorites.size, actualFavorite.size)
    }
}