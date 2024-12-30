package com.mohammadhf.local.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mohammadhf.local.CatsAppDatabase
import com.mohammadhf.local.data_source.cats.CatsImageLocalDataSource
import com.mohammadhf.local.data_source.cats.CatsImageLocalDataSourceImpl
import com.mohammadhf.local.utils.catsImageData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class CatsImageLocalDataSourceTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var catsAppDatabase: CatsAppDatabase
    private lateinit var catsImageLocalDataSource: CatsImageLocalDataSource

    @Before
    fun setup() = runTest {
        hiltRule.inject()

        catsImageLocalDataSource = CatsImageLocalDataSourceImpl(catsAppDatabase.getCatsImageDao())
        catsImageLocalDataSource.updateInsertCatsImages(catsImageData)
    }

    @After
    fun shutdown() {
        catsAppDatabase.close()
    }

    @Test
    fun getCatsImagesTest() = runTest {
        assertThat(catsImageLocalDataSource.getCatsImages()).containsExactlyElementsIn(catsImageData)
    }

    @Test
    fun streamCatsImagesTest() = runTest {
        assertThat(catsImageLocalDataSource.streamCatsImages().first()).containsExactlyElementsIn(catsImageData)
    }

    @Test
    fun getCatsByIdsTest() = runTest {
        val ids = arrayListOf(
            catsImageData[0].catsImageId,
            catsImageData[1].catsImageId
        )
        assertThat(catsImageLocalDataSource.getCatsImagesByIds(ids))
            .containsExactlyElementsIn(catsImageData.filter { it.catsImageId in ids })
    }

    @Test
    fun getCatsImageByIdTest() = runTest {
        assertThat(catsImageLocalDataSource.getCatsImageWithId(catsImageData.first().catsImageId))
            .isEqualTo(catsImageData[0])
    }
}