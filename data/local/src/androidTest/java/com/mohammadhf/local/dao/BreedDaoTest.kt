package com.mohammadhf.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.mohammadhf.local.CatsAppDatabase
import com.mohammadhf.local.utils.breedData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named
import com.google.common.truth.Truth.assertThat

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class BreedDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var catsAppDatabase: CatsAppDatabase
    private lateinit var breedDao: BreedDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()

        breedDao = catsAppDatabase.getBreedDao()
        breedDao.updateOrInsertAllBreeds(breedData)
    }

    @After
    fun shutdown() {
        catsAppDatabase.close()
    }

    @Test
    fun getAllBreedsTest() = runTest {
        assertThat(breedDao.getAllBreeds()).containsExactlyElementsIn(breedData)
    }

    @Test
    fun getBreedByIdTest1() = runTest {
        assertThat(breedDao.getBreedById(breedData.first().breedId)).isEqualTo(breedData.first())
    }

    @Test
    fun getBreedByIdTest2() = runTest {
        assertThat(breedDao.getBreedById(breedData[1].breedId)).isEqualTo(breedData[1])
    }

    @Test
    fun getBreedByIdsTest() = runTest {
        val ids = arrayListOf(
            breedData[0].breedId,
            breedData[1].breedId
        )
        assertThat(breedDao.getBreedByIds(ids))
            .containsExactlyElementsIn(breedData.filter { it.breedId in ids })
    }

    @Test
    fun deleteBreedTest() = runTest {
        breedDao.deleteBreed(breedData[0])
        assertThat(breedDao.getAllBreeds())
            .containsExactlyElementsIn(breedData.filter { it.breedId != breedData[0].breedId })
    }
}