package com.yogaprasetyo.capstone.core.data.source.remote

import com.yogaprasetyo.capstone.core.data.source.remote.network.ApiService
import com.yogaprasetyo.capstone.core.data.source.remote.response.ResponseListMovie
import com.yogaprasetyo.capstone.core.utils.API_KEY
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.dummyMessageEmptyApiKey
import com.yogaprasetyo.capstone.core.utils.testing.DataDummy.generateDummyResponse

class FakeApiService : ApiService {
    private val dummyResponse = generateDummyResponse()

    override suspend fun searchMovies(queries: Map<String, String>): ResponseListMovie {
        checkingQueries(queries)
        return dummyResponse
    }

    override suspend fun discoverMovies(queries: Map<String, String>): ResponseListMovie {
        checkingQueries(queries)
        return dummyResponse
    }

    override suspend fun getTrendingMovies(apiKey: String, page: Int): ResponseListMovie {
        checkingApiKey(apiKey)
        return dummyResponse
    }

    override suspend fun getPopularMovies(queries: Map<String, String>): ResponseListMovie {
        checkingQueries(queries)
        return dummyResponse
    }

    override suspend fun getNowPlayingMovies(queries: Map<String, String>): ResponseListMovie {
        checkingQueries(queries)
        return dummyResponse
    }

    override suspend fun getTopRatedMovies(queries: Map<String, String>): ResponseListMovie {
        checkingQueries(queries)
        return dummyResponse
    }

    private fun checkingQueries(queries: Map<String, String>) {
        if (queries[API_KEY].isNullOrEmpty()) throw Exception(dummyMessageEmptyApiKey)
    }

    private fun checkingApiKey(apiKey: String) {
        if (apiKey.isEmpty()) throw Exception(dummyMessageEmptyApiKey)
    }

}