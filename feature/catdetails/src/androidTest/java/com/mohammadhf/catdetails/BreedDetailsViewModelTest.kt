package com.mohammadhf.catdetails

import com.mohammadhf.catdetails.testrepo.TestableBreedRepository
import com.mohammadhf.usecase.breed.GetBreedByIdUseCase
import org.junit.Before

class BreedDetailsViewModelTest {

    private lateinit var breedDetailsViewModel: BreedDetailsViewModel

    @Before
    fun setup() {
        val breedRepo = TestableBreedRepository()

        breedDetailsViewModel = BreedDetailsViewModel(
            getBreedByIdUseCase = GetBreedByIdUseCase(breedRepo),
            coroutineContextProvider = TestablePlatformCoroutineProvider()
        )
    }
}