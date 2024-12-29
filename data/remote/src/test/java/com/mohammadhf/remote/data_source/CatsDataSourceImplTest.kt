package com.mohammadhf.remote.data_source

import com.mohammadhf.remote.api.CatsApi
import com.mohammadhf.remote.utils.mockCatsImagesResponse1
import com.mohammadhf.remote.utils.mockCatsImagesResponse2
import com.mohammadhf.remote.utils.mockCatsImagesResult1
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import junit.framework.TestCase.assertEquals
import retrofit2.converter.gson.GsonConverterFactory

class CatsDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var catsApi: CatsApi
    private lateinit var catsDataSource: CatsDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer().apply { start() }

        val baseUrl = mockWebServer.url("/").toString()
        catsApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(CatsApi::class.java)

        catsDataSource = CatsDataSourceImpl(catsApi)
    }

    @Test
    fun `test getCatsList success`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setBody(mockCatsImagesResponse1).setResponseCode(200))

        val testResponse = catsDataSource.getCatsList(page = 0, limit = 1)
        assertEquals(mockCatsImagesResult1, testResponse)
    }

    @Test
    fun `test getCatsList hasCorrectSize`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setBody(mockCatsImagesResponse2).setResponseCode(200))

        val testResponse = catsDataSource.getCatsList(page = 0, limit = 2)
        assertEquals(testResponse.size, 2)
    }
}