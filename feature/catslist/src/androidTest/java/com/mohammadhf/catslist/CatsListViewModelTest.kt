package com.mohammadhf.catslist

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mohammadhf.catslist.testrepo.TestableBreedRepository
import com.mohammadhf.catslist.testrepo.TestableCatsImageRepository
import com.mohammadhf.catslist.testrepo.mockCatsImageList
import com.mohammadhf.usecase.cats.StreamAllCatsUseCase
import com.mohammadhf.usecase.cats.ToggleCatFavoriteUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
class CatsListViewModelTest {

    private lateinit var catsListViewModel: CatsListViewModel
    @Before
    fun setup() = runTest {
        val catsRepo = TestableCatsImageRepository()
        val breedRepo = TestableBreedRepository()

        catsListViewModel = CatsListViewModel(
            streamAllCatsUseCase = StreamAllCatsUseCase(catsRepo, breedRepo),
            toggleCatFavoriteUseCase = ToggleCatFavoriteUseCase(catsRepo),
            coroutineContextProvider = TestablePlatformCoroutineProvider()
        )
    }

    @Test
    fun test() = runTest {
        // Couldn't create UI test for state!
    }
}