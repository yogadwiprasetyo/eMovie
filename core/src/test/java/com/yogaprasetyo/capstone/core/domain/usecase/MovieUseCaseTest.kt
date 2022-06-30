package com.yogaprasetyo.capstone.core.domain.usecase

import com.yogaprasetyo.capstone.core.domain.repository.IMovieRepository
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyDataResource
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyFavoriteMovies
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyMovie
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyQueriesGeneral
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyQueriesGenre
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyQueriesSearch
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyState
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyTypeMovie
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldEquals
import com.yogaprasetyo.capstone.core.utils.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var movieRepository: IMovieRepository
    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setup() {
        movieUseCase = MovieInteractor(movieRepository)
    }

    @Test
    fun `should get data searchMovies from repository`() {
        val dummyResult = flowOf(dummyDataResource())
        `when`(movieRepository.searchMovies(dummyQueriesSearch)).thenReturn(dummyResult)
        val moviesByQuery = movieUseCase.searchMovies(dummyQueriesSearch)

        verify(movieRepository).searchMovies(dummyQueriesSearch)
        verifyNoMoreInteractions(movieRepository)

        Assert.assertEquals(shouldEquals, dummyResult, moviesByQuery)
    }

    @Test
    fun `should get data getMoviesByGenre from repository`() {
        val dummyResult = flowOf(dummyDataResource())
        `when`(movieRepository.getMoviesByGenre(dummyQueriesGenre)).thenReturn(dummyResult)
        val moviesByGenre = movieUseCase.getMoviesByGenre(dummyQueriesGenre)

        verify(movieRepository).getMoviesByGenre(dummyQueriesGenre)
        verifyNoMoreInteractions(movieRepository)

        Assert.assertEquals(shouldEquals, dummyResult, moviesByGenre)
    }

    @Test
    fun `should get data getMoviesByType from repository`() {
        val dummyResult = flowOf(dummyDataResource())
        `when`(
            movieRepository.getMoviesByType(
                dummyTypeMovie,
                dummyQueriesGeneral
            )
        ).thenReturn(dummyResult)
        val moviesByType = movieUseCase.getMoviesByType(dummyTypeMovie, dummyQueriesGeneral)

        verify(movieRepository).getMoviesByType(dummyTypeMovie, dummyQueriesGeneral)
        verifyNoMoreInteractions(movieRepository)

        Assert.assertEquals(shouldEquals, dummyResult, moviesByType)
    }

    @Test
    fun `should get data getFavoriteMovie from repository`() {
        val dummyResult = flowOf(dummyFavoriteMovies)
        `when`(movieRepository.getFavoriteMovie()).thenReturn(dummyResult)
        val movieFavorites = movieUseCase.getFavoriteMovie()

        verify(movieRepository).getFavoriteMovie()
        verifyNoMoreInteractions(movieRepository)

        Assert.assertEquals(shouldEquals, dummyResult, movieFavorites)
    }

    @Test
    fun `should invoke setFavoriteMovie from repository`() = runTest {
        movieUseCase.setFavoriteMovie(dummyMovie, dummyState)
        verify(movieRepository).setFavoriteMovie(dummyMovie, dummyState)
    }
}