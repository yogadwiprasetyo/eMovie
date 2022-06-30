package com.yogaprasetyo.capstone.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.yogaprasetyo.capstone.core.data.Resource
import com.yogaprasetyo.capstone.core.domain.model.Movie
import com.yogaprasetyo.capstone.core.domain.usecase.MovieUseCase
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyDataResource
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyQueriesSearch
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldEquals
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.shouldNotNull
import com.yogaprasetyo.capstone.core.utils.testing.MainDispatcherRule
import com.yogaprasetyo.capstone.core.utils.testing.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase
    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        searchViewModel = SearchViewModel(movieUseCase)
    }

    @Test
    fun `when setQuery Invoke Should Have Results`() {
        val dataResource = dummyDataResource()
        val expectedResult = MutableLiveData<Resource<List<Movie>>>()
        expectedResult.value = dataResource

        `when`(movieUseCase.searchMovies(dummyQueriesSearch))
            .thenReturn(flowOf(dataResource))

        searchViewModel.setQuery(dummyQueriesSearch)
        val actualResult = searchViewModel.results.getOrAwaitValue()

        assertNotNull(shouldNotNull, actualResult)
        assertEquals(shouldEquals, expectedResult.value, actualResult)

        verify(movieUseCase).searchMovies(dummyQueriesSearch)
        verifyNoMoreInteractions(movieUseCase)
    }
}