package com.yogaprasetyo.capstone.core.data.source.remote

import android.util.Log
import com.yogaprasetyo.capstone.core.data.source.remote.network.ApiResponse
import com.yogaprasetyo.capstone.core.data.source.remote.network.ApiService
import com.yogaprasetyo.capstone.core.data.source.remote.response.ResponseListMovie
import com.yogaprasetyo.capstone.core.utils.API_KEY
import com.yogaprasetyo.capstone.core.utils.QUERY_API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun searchMovies(queries: MutableMap<String, String>): Flow<ApiResponse<ResponseListMovie>> =
        flow {
            try {
                queries[QUERY_API_KEY] = API_KEY
                val response = apiService.searchMovies(queries)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun discoverMovies(queries: MutableMap<String, String>): Flow<ApiResponse<ResponseListMovie>> =
        flow {
            try {
                queries[QUERY_API_KEY] = API_KEY
                val response = apiService.discoverMovies(queries)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getTrendingMovies(): Flow<ApiResponse<ResponseListMovie>> = flow {
        try {
            val response = apiService.getTrendingMovies(API_KEY)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e(TAG, e.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getPopularMovies(queries: MutableMap<String, String>): Flow<ApiResponse<ResponseListMovie>> =
        flow {
            try {
                queries[QUERY_API_KEY] = API_KEY
                val response = apiService.getPopularMovies(queries)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getNowPlayingMovies(queries: MutableMap<String, String>): Flow<ApiResponse<ResponseListMovie>> =
        flow {
            try {
                queries[QUERY_API_KEY] = API_KEY
                val response = apiService.getNowPlayingMovies(queries)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getTopRatedMovies(queries: MutableMap<String, String>): Flow<ApiResponse<ResponseListMovie>> =
        flow {
            try {
                queries[QUERY_API_KEY] = API_KEY
                val response = apiService.getTopRatedMovies(queries)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    companion object {
        const val TAG = "RemoteDataSource"
    }
}