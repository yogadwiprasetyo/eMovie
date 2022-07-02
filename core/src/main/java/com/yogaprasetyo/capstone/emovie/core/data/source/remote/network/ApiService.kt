package com.yogaprasetyo.capstone.emovie.core.data.source.remote.network

import com.yogaprasetyo.capstone.emovie.core.data.source.remote.response.ResponseListMovie
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("search/movie")
    suspend fun searchMovies(
        @QueryMap queries: Map<String, String>,
    ): ResponseListMovie

    @GET("discover/movie")
    suspend fun discoverMovies(
        @QueryMap queries: Map<String, String>
    ): ResponseListMovie

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): ResponseListMovie

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @QueryMap queries: Map<String, String>
    ): ResponseListMovie

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @QueryMap queries: Map<String, String>
    ): ResponseListMovie

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @QueryMap queries: Map<String, String>
    ): ResponseListMovie
}