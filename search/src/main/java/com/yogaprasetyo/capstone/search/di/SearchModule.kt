package com.yogaprasetyo.capstone.search.di

import com.yogaprasetyo.capstone.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel(get()) }
}