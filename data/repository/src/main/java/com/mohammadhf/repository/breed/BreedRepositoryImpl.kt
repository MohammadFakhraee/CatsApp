package com.mohammadhf.repository.breed

import com.mohammadhf.local.data_source.breed.BreedLocalDataSource
import com.mohammadhf.local.models.BreedLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val breedLocalDataSource: BreedLocalDataSource
) : BreedRepository {

    override fun streamAllBreeds(): Flow<List<BreedLocal>> =
        breedLocalDataSource.streamAllBreeds().distinctUntilChanged()

    override suspend fun getById(id: String): BreedLocal =
        breedLocalDataSource.getBreedById(id)
}