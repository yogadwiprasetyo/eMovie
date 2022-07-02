package com.yogaprasetyo.capstone.emovie.core.data.source.remote

import com.yogaprasetyo.capstone.emovie.core.data.source.remote.network.ApiService
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyApiKey
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyMessageEmptyApiKey
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyQueriesEmptyApiKey
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.dummyQueriesGeneral
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.generateDummyResponse
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldEquals
import com.yogaprasetyo.capstone.emovie.core.utils.testing.DataDummy.shouldNotNull
import com.yogaprasetyo.capstone.emovie.core.utils.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        apiService = FakeApiService()
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun `when searchMovies Should Not Null`() = runTest {
        val expectedResponse = generateDummyResponse()
        val actualResponse = apiService.searchMovies(dummyQueriesGeneral)

        assertNotNull(shouldNotNull, actualResponse)
        assertEquals(shouldEquals, expectedResponse.results.size, actualResponse.results.size)
    }

    @Test
    fun `when discoverMovies Should Not Null`() = runTest {
        val expectedResponse = generateDummyResponse()
        val actualResponse = apiService.discoverMovies(dummyQueriesGeneral)

        assertNotNull(shouldNotNull, actualResponse)
        assertEquals(shouldEquals, expectedResponse.results.size, actualResponse.results.size)
    }

    @Test
    fun `when getTrendingMovies Should Not Null`() = runTest {
        val expectedResponse = generateDummyResponse()
        val actualResponse = apiService.getTrendingMovies(dummyApiKey)

        assertNotNull(shouldNotNull, actualResponse)
        assertEquals(shouldEquals, expectedResponse.results.size, actualResponse.results.size)
    }

    @Test
    fun `when getPopularMovies Should Not Null`() = runTest {
        val expectedResponse = generateDummyResponse()
        val actualResponse = apiService.getPopularMovies(dummyQueriesGeneral)

        assertNotNull(shouldNotNull, actualResponse)
        assertEquals(shouldEquals, expectedResponse.results.size, actualResponse.results.size)
    }

    @Test
    fun `when getNowPlayingMovies Should Not Null`() = runTest {
        val expectedResponse = generateDummyResponse()
        val actualResponse = apiService.getNowPlayingMovies(dummyQueriesGeneral)

        assertNotNull(shouldNotNull, actualResponse)
        assertEquals(shouldEquals, expectedResponse.results.size, actualResponse.results.size)
    }

    @Test
    fun `when getTopRatedMovies Should Not Null`() = runTest {
        val expectedResponse = generateDummyResponse()
        val actualResponse = apiService.getTopRatedMovies(dummyQueriesGeneral)

        assertNotNull(shouldNotNull, actualResponse)
        assertEquals(shouldEquals, expectedResponse.results.size, actualResponse.results.size)
    }

    @Test
    fun `when searchMovies Empty Api Key Should Throw Exception`() {
        val exception = assertThrows(Exception::class.java) {
            runTest { apiService.searchMovies(dummyQueriesEmptyApiKey) }
        }
        assertEquals(shouldEquals, dummyMessageEmptyApiKey, exception.message)
    }

    @Test
    fun `when discoverMovies Empty Api Key Should Throw Exception`() {
        val exception = assertThrows(Exception::class.java) {
            runTest { apiService.discoverMovies(dummyQueriesEmptyApiKey) }
        }
        assertEquals(shouldEquals, dummyMessageEmptyApiKey, exception.message)
    }

    @Test
    fun `when getTrendingMovies Empty Api Key Should Throw Exception`() {
        val exception = assertThrows(Exception::class.java) {
            runTest { apiService.getTrendingMovies("") }
        }
        assertEquals(shouldEquals, dummyMessageEmptyApiKey, exception.message)
    }

    @Test
    fun `when getPopularMovies Empty Api Key Should Throw Exception`() {
        val exception = assertThrows(Exception::class.java) {
            runTest { apiService.getPopularMovies(dummyQueriesEmptyApiKey) }
        }
        assertEquals(shouldEquals, dummyMessageEmptyApiKey, exception.message)
    }

    @Test
    fun `when getNowPlayingMovies Empty Api Key Should Throw Exception`() {
        val exception = assertThrows(Exception::class.java) {
            runTest { apiService.getNowPlayingMovies(dummyQueriesEmptyApiKey) }
        }
        assertEquals(shouldEquals, dummyMessageEmptyApiKey, exception.message)
    }

    @Test
    fun `when getTopRatedMovies Empty Api Key Should Throw Exception`() {
        val exception = assertThrows(Exception::class.java) {
            runTest { apiService.getTopRatedMovies(dummyQueriesEmptyApiKey) }
        }
        assertEquals(shouldEquals, dummyMessageEmptyApiKey, exception.message)
    }
}