package com.mohammadhf.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohammadhf.local.models.CatsImageLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CatsImageDao {
    @Upsert
    suspend fun updateInsertCatsImages(catsImages: List<CatsImageLocal>)

    @Query("SELECT * FROM CatsImageLocal")
    suspend fun getCatsImages(): List<CatsImageLocal>

    @Query("SELECT * FROM CatsImageLocal")
    fun streamCatsImages(): Flow<List<CatsImageLocal>>

    @Query("SELECT * FROM CatsImageLocal WHERE catsImageId IN (:ids)")
    suspend fun getCatsImagesByIds(ids: List<String>): List<CatsImageLocal>

    @Query("SELECT * FROM CatsImageLocal WHERE catsImageId LIKE :id")
    suspend fun getCatsImageWithId(id: String): CatsImageLocal
}