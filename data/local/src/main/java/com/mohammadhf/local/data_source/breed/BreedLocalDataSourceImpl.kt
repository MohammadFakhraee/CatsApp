package com.mohammadhf.local.data_source.breed

import com.mohammadhf.local.dao.BreedDao
import com.mohammadhf.local.models.BreedLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreedLocalDataSourceImpl @Inject constructor(
    private val breedDao: BreedDao
) : BreedLocalDataSource {
    override suspend fun updateOrInsertAllBreeds(breedsLocal: List<BreedLocal>) =
        breedDao.updateOrInsertAllBreeds(breedsLocal)

    override suspend fun deleteBreed(breedLocal: BreedLocal) =
        breedDao.deleteBreed(breedLocal)

    override suspend fun getAllBreeds(): List<BreedLocal> =
        breedDao.getAllBreeds()

    override fun streamAllBreeds(): Flow<List<BreedLocal>> =
        breedDao.streamAllBreeds()

    override suspend fun getBreedById(id: String): BreedLocal =
        breedDao.getBreedById(id)

    override suspend fun getBreedsByIds(ids: List<String>): List<BreedLocal> =
        breedDao.getBreedByIds(ids)
}