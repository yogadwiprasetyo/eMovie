package com.yogaprasetyo.capstone.emovie

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.yogaprasetyo.capstone.core.di.databaseModule
import com.yogaprasetyo.capstone.core.di.networkModule
import com.yogaprasetyo.capstone.core.di.repositoryModule
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

    // Avoid the 64K limit problem
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}