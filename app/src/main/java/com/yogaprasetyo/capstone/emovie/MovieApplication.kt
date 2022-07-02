package com.yogaprasetyo.capstone.emovie

import android.app.Application
import com.yogaprasetyo.capstone.emovie.core.di.databaseModule
import com.yogaprasetyo.capstone.emovie.core.di.networkModule
import com.yogaprasetyo.capstone.emovie.core.di.repositoryModule
import com.yogaprasetyo.capstone.emovie.di.useCaseModule
import com.yogaprasetyo.capstone.emovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}