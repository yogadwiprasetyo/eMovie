package com.yogaprasetyo.capstone.emovie.detail

import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieUseCase
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyMovie
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyState
import com.yogaprasetyo.capstone.emovie.core.utils.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(movieUseCase)
    }

    @Test
    fun `when updateFavoriteMovie Called Should Invoke setFavoriteMovie Function`() = runTest {
        detailViewModel.updateFavoriteMovie(dummyMovie, dummyState)
        verify(movieUseCase).setFavoriteMovie(dummyMovie, dummyState)
        verifyNoMoreInteractions(movieUseCase)
    }
}