package com.yogaprasetyo.capstone.core.data.source.local.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyTrending
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.generateDummyListMovieEntity
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldEquals
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldFalse
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldNotNull
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldTrue
import com.yogaprasetyo.capstone.core.utils.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieDaoTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var db: MovieDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).build()
        dao = db.movieDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun addFavoriteMovie_Success() = runTest {
        val dummyMoviesEntity = generateDummyListMovieEntity()
        val dummyMovieFavorite = dummyMoviesEntity.first { !it.isFavorite }
        dummyMovieFavorite.isFavorite = true

        dao.insertMovie(dummyMoviesEntity)
        dao.updateFavoriteMovie(dummyMovieFavorite)

        val actualFavoriteMovie = dao.getFavoriteMovies().first()
        assertTrue(shouldTrue, actualFavoriteMovie.contains(dummyMovieFavorite))
    }

    @Test
    fun removeFavoriteMovie_Success() = runTest {
        val dummyMoviesEntity = generateDummyListMovieEntity()
        val dummyMovieFavorite = dummyMoviesEntity.first { it.isFavorite }
        dummyMovieFavorite.isFavorite = false

        dao.insertMovie(dummyMoviesEntity)
        dao.updateFavoriteMovie(dummyMovieFavorite)

        val actualFavoriteMovie = dao.getFavoriteMovies().first()
        assertFalse(shouldFalse, actualFavoriteMovie.contains(dummyMovieFavorite))
    }

    @Test
    fun getFavoriteMovies_NotNull() = runTest {
        val dummyMoviesEntity = generateDummyListMovieEntity()
        val expectedData = dummyMoviesEntity.filter { it.isFavorite }

        dao.insertMovie(dummyMoviesEntity)
        val actualData = dao.getFavoriteMovies().first()

        assertNotNull(shouldNotNull, actualData)
        assertEquals(shouldEquals, expectedData.size, actualData.size)
    }

    @Test
    fun getMoviesByType_NotNull() = runTest {
        val dummyMoviesEntity = generateDummyListMovieEntity()
        val expectedData = dummyMoviesEntity.filter { it.type == dummyTrending }

        dao.insertMovie(dummyMoviesEntity)
        val actualData = dao.getMoviesByType(dummyTrending).first()

        assertNotNull(shouldNotNull, actualData)
        assertEquals(shouldEquals, expectedData.size, actualData.size)
    }
}