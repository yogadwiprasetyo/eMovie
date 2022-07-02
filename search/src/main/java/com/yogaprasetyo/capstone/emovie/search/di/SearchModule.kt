package com.yogaprasetyo.capstone.emovie.search.di

import com.yogaprasetyo.capstone.emovie.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel(get()) }
}