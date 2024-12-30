package com.mohammadhf.repository.breed

import com.mohammadhf.local.models.BreedLocal
import kotlinx.coroutines.flow.Flow

interface BreedRepository {
    fun streamAllBreeds(): Flow<List<BreedLocal>>
    suspend fun getById(id: String): BreedLocal
}