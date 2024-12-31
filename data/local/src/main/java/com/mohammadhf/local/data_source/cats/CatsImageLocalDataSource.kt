package com.mohammadhf.local.data_source.cats

import com.mohammadhf.local.models.CatsImageLocal
import kotlinx.coroutines.flow.Flow

interface CatsImageLocalDataSource {
    suspend fun updateInsertCatsImages(catsImages: List<CatsImageLocal>)
    suspend fun getCatsImages(): List<CatsImageLocal>
    fun streamCatsImages(): Flow<List<CatsImageLocal>>
    suspend fun getCatsImagesByIds(ids: List<String>): List<CatsImageLocal>
    suspend fun getCatsImageWithId(id: String): CatsImageLocal
}