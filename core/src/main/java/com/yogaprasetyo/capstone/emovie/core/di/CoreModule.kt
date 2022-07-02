package com.yogaprasetyo.capstone.emovie.core.di

import androidx.room.Room
import com.yogaprasetyo.capstone.emovie.core.data.MovieRepository
import com.yogaprasetyo.capstone.emovie.core.data.source.local.LocalDataSource
import com.yogaprasetyo.capstone.emovie.core.data.source.local.room.MovieDatabase
import com.yogaprasetyo.capstone.emovie.core.data.source.remote.RemoteDataSource
import com.yogaprasetyo.capstone.emovie.core.data.source.remote.network.ApiService
import com.yogaprasetyo.capstone.emovie.core.domain.repository.IMovieRepository
import com.yogaprasetyo.capstone.emovie.core.utils.BASE_URL
import com.yogaprasetyo.capstone.emovie.core.utils.SECRET_PASSPHRASE
import com.yogaprasetyo.capstone.emovie.core.utils.getCertificatePinner
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(SECRET_PASSPHRASE)
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movies.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(getCertificatePinner())
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMovieRepository> { MovieRepository(get(), get()) }
}