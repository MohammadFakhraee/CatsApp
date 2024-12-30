package com.mohammadhf.local.data_source.breed

import com.mohammadhf.local.models.BreedLocal
import kotlinx.coroutines.flow.Flow

interface BreedLocalDataSource {
    suspend fun updateOrInsertAllBreeds(breedsLocal: List<BreedLocal>)
    suspend fun deleteBreed(breedLocal: BreedLocal)
    suspend fun getAllBreeds(): List<BreedLocal>
    fun streamAllBreeds(): Flow<List<BreedLocal>>
    suspend fun getBreedById(id: String): BreedLocal
    suspend fun getBreedsByIds(ids: List<String>): List<BreedLocal>
}