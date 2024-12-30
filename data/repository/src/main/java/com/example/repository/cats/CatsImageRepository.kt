package com.example.repository.cats

import com.mohammadhf.local.models.CatsImageLocal
import kotlinx.coroutines.flow.Flow

interface CatsImageRepository {

//    suspend fun getAllCats(): List<CatsImageLocal>
    fun streamAllCats(): Flow<List<CatsImageLocal>>
    suspend fun toggleCatFavorite(id: String)
}