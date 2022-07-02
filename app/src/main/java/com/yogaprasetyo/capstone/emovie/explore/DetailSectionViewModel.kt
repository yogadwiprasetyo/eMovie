package com.yogaprasetyo.capstone.emovie.explore

import androidx.lifecycle.*
import com.yogaprasetyo.capstone.emovie.core.data.Resource
import com.yogaprasetyo.capstone.emovie.core.domain.model.Movie
import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieUseCase
import com.yogaprasetyo.capstone.emovie.core.utils.GENRE_KEY

class DetailSectionViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    private val _args = MutableLiveData<DetailSectionActivityArgs>()

    val results: LiveData<Resource<List<Movie>>> = _args.switchMap {
        if (it.genreId != DEFAULT_ID) {
            val queries = mutableMapOf(GENRE_KEY to it.genreId.toString())
            movieUseCase.getMoviesByGenre(queries) // Load movie by genre id
        } else {
            movieUseCase.getMoviesByType(it.typeMovie, mutableMapOf()) // Load movie by type
        }.asLiveData()
    }

    fun setArgs(args: DetailSectionActivityArgs) {
        _args.value = args
    }

    companion object {
        private const val DEFAULT_ID = 0
    }
}