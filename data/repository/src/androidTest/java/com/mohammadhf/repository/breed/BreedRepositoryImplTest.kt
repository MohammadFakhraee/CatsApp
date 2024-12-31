package com.mohammadhf.repository.breed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mohammadhf.local.CatsAppDatabase
import com.mohammadhf.local.data_source.breed.BreedLocalDataSource
import com.mohammadhf.local.data_source.breed.BreedLocalDataSourceImpl
import com.mohammadhf.repository.utils.mockLocalBreed1
import com.mohammadhf.repository.utils.mockLocalBreed2
import com.mohammadhf.repository.utils.mockLocalBreedsList
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
class BreedRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var catsAppDatabase: CatsAppDatabase
    private lateinit var breedLocalDataSource: BreedLocalDataSource
    private lateinit var breedRepository: BreedRepository

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        breedLocalDataSource = BreedLocalDataSourceImpl(catsAppDatabase.getBreedDao())
        breedLocalDataSource.updateOrInsertAllBreeds(mockLocalBreedsList)
        breedRepository = BreedRepositoryImpl(breedLocalDataSource)
    }

    @After
    fun shutdown() {
        catsAppDatabase.close()
    }

    @Test
    fun testStreamAllBreedsSuccess() = runTest {
        assertThat(breedRepository.streamAllBreeds().first())
            .containsExactlyElementsIn(mockLocalBreedsList)
    }

    @Test
    fun testStreamAllBreedsFail() = runTest {
        breedLocalDataSource.deleteBreed(mockLocalBreed1)
        assertThat(breedRepository.streamAllBreeds().first())
            .doesNotContain(mockLocalBreed1)
    }

    @Test
    fun testGetByIdSuccess() = runTest {
        assertThat(breedRepository.getById(mockLocalBreed1.breedId))
            .isEqualTo(mockLocalBreed1)
    }

    @Test
    fun testGetByIdFail() = runTest {
        assertThat(breedRepository.getById(mockLocalBreed1.breedId))
            .isNotEqualTo(mockLocalBreed2)
    }
}