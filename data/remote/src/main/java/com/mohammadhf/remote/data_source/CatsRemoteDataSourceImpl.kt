package com.mohammadhf.remote.data_source

import com.mohammadhf.remote.api.CatsApi
import com.mohammadhf.remote.models.CatsImagesResponse
import javax.inject.Inject

class CatsRemoteDataSourceImpl @Inject constructor(
    private val catsApi: CatsApi
) : CatsRemoteDataSource {

    override suspend fun getCatsList(page: Int, limit: Int): List<CatsImagesResponse> =
        catsApi.getCatsList(page = page, limit = limit)
}