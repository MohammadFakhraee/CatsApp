package com.mohammadhf.catslist

import com.mohammadhf.catslist.testrepo.TestableBreedRepository
import com.mohammadhf.catslist.testrepo.TestableCatsImageRepository
import com.mohammadhf.core.utils.PlatformCoroutineProvider
import com.mohammadhf.usecase.cats.StreamAllCatsUseCase
import com.mohammadhf.usecase.cats.ToggleCatFavoriteUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before

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
}