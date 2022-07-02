package com.yogaprasetyo.capstone.emovie.di

import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieInteractor
import com.yogaprasetyo.capstone.emovie.core.domain.usecase.MovieUseCase
import com.yogaprasetyo.capstone.emovie.detail.DetailViewModel
import com.yogaprasetyo.capstone.emovie.explore.DetailSectionViewModel
import com.yogaprasetyo.capstone.emovie.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { DetailSectionViewModel(get()) }
}