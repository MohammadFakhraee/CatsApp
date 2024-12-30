package com.mohammadhf.repository.cats

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mohammadhf.local.CatsAppDatabase
import com.mohammadhf.local.data_source.breed.BreedLocalDataSource
import com.mohammadhf.local.data_source.breed.BreedLocalDataSourceImpl
import com.mohammadhf.local.data_source.cats.CatsImageLocalDataSource
import com.mohammadhf.local.data_source.cats.CatsImageLocalDataSourceImpl
import com.mohammadhf.remote.api.CatsApi
import com.mohammadhf.remote.data_source.CatsRemoteDataSource
import com.mohammadhf.remote.data_source.CatsRemoteDataSourceImpl
import com.mohammadhf.repository.mapper.BreedRemoteToLocalMapper
import com.mohammadhf.repository.mapper.CatsImageRemoteToLocalMapper
import com.mohammadhf.repository.utils.mockCatsImageServerResponse
import com.mohammadhf.repository.utils.mockLocalCatsImage1
import com.mohammadhf.repository.utils.mockLocalCatsImageList
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class CatsImageRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var catsAppDatabase: CatsAppDatabase

    private lateinit var breedLocalDataSource: BreedLocalDataSource
    private lateinit var catsImageLocalDataSource: CatsImageLocalDataSource

    private lateinit var mockWebServer: MockWebServer
    private lateinit var catsApi: CatsApi
    private lateinit var catsRemoteDataSource: CatsRemoteDataSource

    private lateinit var catsImageRepository: CatsImageRepository

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        breedLocalDataSource = BreedLocalDataSourceImpl(catsAppDatabase.getBreedDao())
        catsImageLocalDataSource = CatsImageLocalDataSourceImpl(catsAppDatabase.getCatsImageDao())

        mockWebServer = MockWebServer().apply { start() }

        val baseUrl = mockWebServer.url("/").toString()
        catsApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(CatsApi::class.java)

        catsRemoteDataSource = CatsRemoteDataSourceImpl(catsApi)

        mockWebServer.enqueue(MockResponse().setBody(mockCatsImageServerResponse).setResponseCode(200))

        catsImageRepository = CatsImageRepositoryImpl(
            catsImageLocalDataSource = catsImageLocalDataSource,
            catsRemoteDataSource = catsRemoteDataSource,
            catsImageRemoteToLocalMapper = CatsImageRemoteToLocalMapper(BreedRemoteToLocalMapper()),
            breedLocalDataSource = breedLocalDataSource
        )
    }

    @After
    fun shutdown() {
        catsAppDatabase.close()
    }

    @Test
    fun testCatsSizeInDB() = runTest {
        val result = catsImageRepository.streamAllCats().first()
        assertThat(result.size).isEqualTo(mockLocalCatsImageList.size)
    }

    @Test
    fun testStreamAllDataEquity() = runTest {
        val result = catsImageRepository.streamAllCats().first()
        assertThat(result).containsExactlyElementsIn(mockLocalCatsImageList)
    }

    @Test
    fun testToggleFavorite() = runTest {
        catsImageRepository.streamAllCats().first()
        catsImageRepository.toggleCatFavorite(mockLocalCatsImage1.catsImageId)

        assertThat(
            catsImageLocalDataSource.getCatsImages()
                .find { it.catsImageId == mockLocalCatsImage1.catsImageId }!!.isFavored
        ).isTrue()
    }

    @Test
    fun testDoubleToggleFavorite() = runTest {
        catsImageRepository.streamAllCats().first()
        catsImageRepository.toggleCatFavorite(mockLocalCatsImage1.catsImageId)
        catsImageRepository.toggleCatFavorite(mockLocalCatsImage1.catsImageId)

        assertThat(
            catsImageLocalDataSource.getCatsImages()
                .find { it.catsImageId == mockLocalCatsImage1.catsImageId }!!.isFavored
        ).isFalse()
    }

    @Test
    fun testFavoritePersistAfterRetry() = runTest {
        catsImageRepository.streamAllCats().first()
        catsImageRepository.toggleCatFavorite(mockLocalCatsImage1.catsImageId)

        mockWebServer.enqueue(MockResponse().setBody(mockCatsImageServerResponse).setResponseCode(200))
        catsImageRepository.streamAllCats().first()

        assertThat(
            catsImageLocalDataSource.getCatsImages()
                .find { it.catsImageId == mockLocalCatsImage1.catsImageId }!!.isFavored
        ).isTrue()
    }
}