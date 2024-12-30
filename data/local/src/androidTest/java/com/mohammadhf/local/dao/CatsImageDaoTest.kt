package com.mohammadhf.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mohammadhf.local.CatsAppDatabase
import com.mohammadhf.local.utils.breedData
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
class CatsImageDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var catsAppDatabase: CatsAppDatabase
    private lateinit var catsImageDao: CatsImageDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()

        catsImageDao = catsAppDatabase.getCatsImageDao()
        catsImageDao.updateInsertCatsImages(catsImageData)
    }

    @After
    fun shutdown() {
        catsAppDatabase.close()
    }

    @Test
    fun getCatsImagesTest() = runTest {
        assertThat(catsImageDao.getCatsImages()).containsExactlyElementsIn(catsImageData)
    }

    @Test
    fun streamCatsImagesTest() = runTest {
        assertThat(catsImageDao.streamCatsImages().first()).containsExactlyElementsIn(catsImageData)
    }

    @Test
    fun getCatsByIdsTest() = runTest {
        val ids = arrayListOf(
            catsImageData[0].catsImageId,
            catsImageData[1].catsImageId
        )
        assertThat(catsImageDao.getCatsImagesByIds(ids))
            .containsExactlyElementsIn(catsImageData.filter { it.catsImageId in ids })
    }

    @Test
    fun getCatsImageByIdTest() = runTest {
        assertThat(catsImageDao.getCatsImageWithId(catsImageData.first().catsImageId))
            .isEqualTo(catsImageData[0])
    }
}