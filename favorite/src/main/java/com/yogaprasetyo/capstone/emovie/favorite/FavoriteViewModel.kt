package com.yogaprasetyo.capstone.emovie.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun loadFavoriteMovies() = movieUseCase.getFavoriteMovie().asLiveData()

    fun updateFavoriteMovie(movie: Movie, newState: Boolean) {
        viewModelScope.launch {
            movieUseCase.setFavoriteMovie(movie, newState)
        }
    }
}