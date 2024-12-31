package com.mohammadhf.remote.data_source

import com.mohammadhf.remote.models.CatsImagesResponse

interface CatsRemoteDataSource {

    suspend fun getCatsList(
        page: Int,
        limit: Int
    ): List<CatsImagesResponse>
}