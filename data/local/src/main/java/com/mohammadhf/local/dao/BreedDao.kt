package com.mohammadhf.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mohammadhf.local.models.BreedLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {
    @Upsert
    suspend fun updateOrInsertAllBreeds(breedsLocal: List<BreedLocal>)

    @Delete
    suspend fun deleteBreed(breedLocal: BreedLocal)

    @Query("SELECT * FROM BreedLocal")
    suspend fun getAllBreeds(): List<BreedLocal>

    @Query("SELECT * FROM BreedLocal")
    fun streamAllBreeds(): Flow<List<BreedLocal>>

    @Query("SELECT * FROM BreedLocal WHERE breedId LIKE :id")
    suspend fun getBreedById(id: String): BreedLocal

    @Query("SELECT * FROM BreedLocal WHERE breedId IN (:ids)")
    suspend fun getBreedByIds(ids: List<String>): List<BreedLocal>
}