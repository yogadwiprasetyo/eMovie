package com.yogaprasetyo.capstone.emovie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun updateFavoriteMovie(movie: Movie, newState: Boolean) {
        viewModelScope.launch {
            movieUseCase.setFavoriteMovie(movie, newState)
        }
    }
}