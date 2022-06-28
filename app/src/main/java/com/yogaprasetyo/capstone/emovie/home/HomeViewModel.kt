package com.yogaprasetyo.capstone.emovie.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yogaprasetyo.capstone.core.domain.usecase.MovieUseCase
import com.yogaprasetyo.capstone.core.utils.TypeMovie

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun loadMovieByType(type: TypeMovie, queries: MutableMap<String, String>) =
        movieUseCase.getMoviesByType(type, queries).asLiveData()
}