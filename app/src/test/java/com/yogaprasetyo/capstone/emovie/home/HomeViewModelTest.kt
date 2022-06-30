package com.yogaprasetyo.capstone.emovie.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.yogaprasetyo.capstone.core.data.Resource
import com.yogaprasetyo.capstone.core.domain.model.Movie
import com.yogaprasetyo.capstone.core.domain.usecase.MovieUseCase
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyDataResource
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyQueriesGeneral
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyTypeMovie
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldNotNull
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldTrue
import com.yogaprasetyo.capstone.core.utils.testing.MainDispatcherRule
import com.yogaprasetyo.capstone.core.utils.testing.getOrAwaitValue

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(movieUseCase)
    }

    @Test
    fun `when loadMovieByType Should Success and Not Null`() {
        val expectedResult = MutableLiveData<Resource<List<Movie>>>()
        expectedResult.value = dummyDataResource()

        `when`(movieUseCase.getMoviesByType(dummyTypeMovie, dummyQueriesGeneral))
            .thenReturn(flowOf(dummyDataResource()))
        val actualResult = homeViewModel
            .loadMovieByType(dummyTypeMovie, dummyQueriesGeneral)
            .getOrAwaitValue()

        assertNotNull(shouldNotNull, actualResult)
        assertTrue(shouldTrue, actualResult is Resource.Success)
    }
}