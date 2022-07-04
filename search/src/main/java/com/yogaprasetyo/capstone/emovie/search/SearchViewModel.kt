package com.yogaprasetyo.capstone.emovie.search

import androidx.lifecycle.*
import com.yogaprasetyo.capstone.emovie.core.data.Resource
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieUseCase

class SearchViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    private var _query = MutableLiveData<MutableMap<String, String>>()

    val results: LiveData<Resource<List<Movie>>> =
        _query.switchMap {
            movieUseCase.searchMovies(it).asLiveData()
        }

    fun setQuery(queries: MutableMap<String, String>) {
        _query.postValue(queries)
    }
}