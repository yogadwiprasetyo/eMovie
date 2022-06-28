package com.yogaprasetyo.capstone.search

import androidx.lifecycle.*
import com.yogaprasetyo.capstone.core.data.Resource
import com.yogaprasetyo.capstone.core.domain.model.Movie
import com.yogaprasetyo.capstone.core.domain.usecase.MovieUseCase

class SearchViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    private var _query = MutableLiveData<MutableMap<String, String>>()

    val results: LiveData<Resource<List<Movie>>> =
        _query.switchMap {
            movieUseCase.searchMovies(it).asLiveData()
        }

    fun setQuery(queries: MutableMap<String, String>) {
        _query.value = queries
    }
}