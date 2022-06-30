package com.yogaprasetyo.capstone.core.utils.testing

import com.yogaprasetyo.capstone.core.data.Resource
import com.yogaprasetyo.capstone.core.data.source.local.entity.MovieEntity
import com.yogaprasetyo.capstone.core.data.source.remote.response.ResponseListMovie
import com.yogaprasetyo.capstone.core.data.source.remote.response.ResultsItem
import com.yogaprasetyo.capstone.core.domain.model.Movie
import com.yogaprasetyo.capstone.core.utils.API_KEY
import com.yogaprasetyo.capstone.core.utils.GENRE_KEY
import com.yogaprasetyo.capstone.core.utils.QUERY_KEY
import com.yogaprasetyo.capstone.core.utils.TypeMovie
import kotlin.random.Random

object DataDummy {
    private const val dummyGenres = "Adventure, Action, Drama"
    private const val dummyDoubleFrom = 1.0
    private const val dummyDoubleUntil = 10.0
    private const val dummyIntFrom = 1
    private const val dummyIntUntil = 1000

    const val shouldFalse = "Should Return False"
    const val shouldTrue = "Should Return True"
    const val shouldEquals = "Should Return Equals"
    const val shouldNotNull = "Should Return Not Null"

    const val dummyState = true
    const val dummyGenreId = 20
    const val dummyApiKey = "ini api key"
    const val dummyMessageEmptyApiKey = "API KEY is not exist"

    val dummyTypeMovie = TypeMovie.POPULAR
    val dummyTrending = TypeMovie.TRENDING.name
    val dummyQueriesSearch = mutableMapOf(QUERY_KEY to "query")
    val dummyQueriesEmptyApiKey = mutableMapOf("key" to "value")
    val dummyQueriesGeneral = mutableMapOf(API_KEY to dummyApiKey)
    val dummyQueriesGenre = mutableMapOf(GENRE_KEY to "$dummyGenreId")
    val dummyMovie = generateDummyListMovie().first { !it.isFavorite }
    val dummyFavoriteMovies = generateDummyListMovie().filter { it.isFavorite }

    fun dummyDataResource(): Resource<List<Movie>> =
        Resource.Success(generateDummyListMovie())

    private fun generateDummyListMovie(): List<Movie> {
        val listMovie = ArrayList<Movie>()
        for (i in 0..10) {
            listMovie.add(
                Movie(
                    movieId = i,
                    title = "Title $i",
                    poster = "Poster $i",
                    genres = dummyGenres,
                    overview = "Overview",
                    releaseDate = "Date $i",
                    backdrop = "Backdrop $i",
                    adult = arrayOf(true, false).random(),
                    isFavorite = arrayOf(true, false).random(),
                    rating = Random.nextDouble(dummyDoubleFrom, dummyDoubleUntil),
                    ratingCount = Random.nextInt(dummyIntFrom, dummyIntUntil)
                )
            )
        }
        return listMovie
    }

    fun generateDummyListMovieEntity(): List<MovieEntity> {
        val listMovie = ArrayList<MovieEntity>()
        for (i in 0..10) {
            listMovie.add(
                MovieEntity(
                    movieId = i,
                    title = "Title $i",
                    poster = "Poster $i",
                    genres = dummyGenres,
                    overview = "Overview",
                    releaseDate = "Date $i",
                    backdrop = "Backdrop $i",
                    type = randomizeTypeMovie(i),
                    adult = arrayOf(true, false).random(),
                    isFavorite = arrayOf(true, false).random(),
                    rating = Random.nextDouble(dummyDoubleFrom, dummyDoubleUntil),
                    ratingCount = Random.nextInt(dummyIntFrom, dummyIntUntil),
                )
            )
        }
        return listMovie
    }

    private fun randomizeTypeMovie(index: Int): String {
        val allValues = TypeMovie.values()
        return if (index >= allValues.size) {
            allValues[0].name
        } else {
            allValues[index].name
        }
    }

    fun generateDummyResponse(): ResponseListMovie {
        return ResponseListMovie(
            page = dummyIntFrom,
            totalResults = dummyIntUntil,
            totalPages = Random.nextInt(),
            results = generateDummyResponseListMovie(),
        )
    }

    private fun generateDummyResponseListMovie(): List<ResultsItem> {
        val listMovie = ArrayList<ResultsItem>()
        for (i in 0..10) {
            listMovie.add(
                ResultsItem(
                    id = i,
                    adult = false,
                    video = false,
                    title = "Famous $i",
                    overview = "Ini overview $i",
                    originalLanguage = "English",
                    originalTitle = "Famous $i",
                    genreIds = listOf(1, 2, 3),
                    posterPath = "Ini poster path $i",
                    backdropPath = "Ini backdrop path $i",
                    releaseDate = "Ini date $i",
                    popularity = Random.nextDouble(),
                    voteAverage = Random.nextDouble(),
                    voteCount = Random.nextInt()
                )
            )
        }
        return listMovie
    }
}
