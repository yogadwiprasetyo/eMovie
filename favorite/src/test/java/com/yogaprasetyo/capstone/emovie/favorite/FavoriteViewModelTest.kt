package com.yogaprasetyo.capstone.emovie.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieUseCase
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyFavoriteMovies
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyMovie
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyState
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldEquals
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldNotNull
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldTrue
import com.yogaprasetyo.capstone.emovie.core.utils.testing.MainDispatcherRule
import com.yogaprasetyo.capstone.emovie.core.utils.testing.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase
    private lateinit var favoriteViewModel: FavoriteViewModel

    @Before
    fun setup() {
        favoriteViewModel = FavoriteViewModel(movieUseCase)
    }

    @Test
    fun `when loadFavoriteMovies Should Not Null`() {
        val expectedData = dummyFavoriteMovies

        `when`(movieUseCase.getFavoriteMovie())
            .thenReturn(flowOf(expectedData))

        val actualData = favoriteViewModel.loadFavoriteMovies().getOrAwaitValue()

        assertNotNull(shouldNotNull, actualData)
        assertEquals(shouldEquals, expectedData[0].movieId, actualData[0].movieId)
        assertTrue(shouldTrue, actualData[0].isFavorite)
    }

    @Test
    fun `when updateFavoriteMovie Called Should setFavoriteMovie Invoke`() = runTest {
        favoriteViewModel.updateFavoriteMovie(dummyMovie, dummyState)

        verify(movieUseCase).setFavoriteMovie(dummyMovie, dummyState)
        verifyNoMoreInteractions(movieUseCase)
    }
}