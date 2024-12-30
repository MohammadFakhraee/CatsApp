package com.mohammadhf.local.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mohammadhf.local.CatsAppDatabase
import com.mohammadhf.local.data_source.breed.BreedLocalDataSource
import com.mohammadhf.local.data_source.breed.BreedLocalDataSourceImpl
import com.mohammadhf.local.utils.breedData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class BreedLocalDataSourceTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var catsAppDatabase: CatsAppDatabase
    private lateinit var breedLocalDataSource: BreedLocalDataSource

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        breedLocalDataSource = BreedLocalDataSourceImpl(catsAppDatabase.getBreedDao())
        breedLocalDataSource.updateOrInsertAllBreeds(breedData)
    }

    @After
    fun shutdown() {
        catsAppDatabase.close()
    }

    @Test
    fun getAllBreedsTest() = runTest {
        assertThat(breedLocalDataSource.getAllBreeds()).containsExactlyElementsIn(breedData)
    }

    @Test
    fun getBreedByIdTest1() = runTest {
        assertThat(breedLocalDataSource.getBreedById(breedData.first().breedId)).isEqualTo(breedData.first())
    }

    @Test
    fun getBreedByIdTest2() = runTest {
        assertThat(breedLocalDataSource.getBreedById(breedData[1].breedId)).isEqualTo(breedData[1])
    }

    @Test
    fun getBreedByIdsTest() = runTest {
        val ids = arrayListOf(
            breedData[0].breedId,
            breedData[1].breedId
        )
        assertThat(breedLocalDataSource.getBreedsByIds(ids))
            .containsExactlyElementsIn(breedData.filter { it.breedId in ids })
    }

    @Test
    fun deleteBreedTest() = runTest {
        breedLocalDataSource.deleteBreed(breedData[0])
        assertThat(breedLocalDataSource.getAllBreeds())
            .containsExactlyElementsIn(breedData.filter { it.breedId != breedData[0].breedId })
    }
}