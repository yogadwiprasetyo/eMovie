package com.yogaprasetyo.capstone.emovie.explore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.yogaprasetyo.capstone.emovie.core.data.Resource
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieUseCase
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyDataResource
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyGenreId
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyQueriesGenre
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyTypeMovie
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldEquals
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldNotNull
import com.yogaprasetyo.capstone.emovie.core.utils.testing.MainDispatcherRule
import com.yogaprasetyo.capstone.emovie.core.utils.testing.getOrAwaitValue
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
class DetailSectionViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val dummyArgsGenreId = DetailSectionActivityArgs(genreId = dummyGenreId)
    private val dummyArgsTypeMovie = DetailSectionActivityArgs(typeMovie = dummyTypeMovie)

    @Mock
    private lateinit var movieUseCase: MovieUseCase
    private lateinit var detailSectionViewModel: DetailSectionViewModel

    @Before
    fun setup() {
        detailSectionViewModel = DetailSectionViewModel(movieUseCase)
    }

    @Test
    fun `when setArgs Invoke Should Have Results`() {
        val dataResource = dummyDataResource()
        val expectedResult = MutableLiveData<Resource<List<Movie>>>()
        expectedResult.value = dataResource

        `when`(movieUseCase.getMoviesByGenre(dummyQueriesGenre))
            .thenReturn(flowOf(dataResource))

        detailSectionViewModel.setArgs(dummyArgsGenreId)
        val actualResult = detailSectionViewModel.results.getOrAwaitValue()

        assertNotNull(shouldNotNull, actualResult)
        assertEquals(shouldEquals, expectedResult.value, actualResult)
    }

    @Test
    fun `when setArgs Fill With GenreId Should Only getMoviesByGenre Invoke`() {
        `when`(movieUseCase.getMoviesByGenre(dummyQueriesGenre))
            .thenReturn(flowOf(dummyDataResource()))

        detailSectionViewModel.setArgs(dummyArgsGenreId)
        detailSectionViewModel.results.getOrAwaitValue()

        verify(movieUseCase).getMoviesByGenre(dummyQueriesGenre)
        verifyNoMoreInteractions(movieUseCase)
    }

    @Test
    fun `when setArgs Fill With TypeMovie Should Only getMoviesByType Invoke`() {
        `when`(movieUseCase.getMoviesByType(dummyTypeMovie, mutableMapOf()))
            .thenReturn(flowOf(dummyDataResource()))

        detailSectionViewModel.setArgs(dummyArgsTypeMovie)
        detailSectionViewModel.results.getOrAwaitValue()

        verify(movieUseCase).getMoviesByType(dummyTypeMovie, mutableMapOf())
        verifyNoMoreInteractions(movieUseCase)
    }
}
