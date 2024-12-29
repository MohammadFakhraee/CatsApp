package com.mohammadhf.remote.api

import com.mohammadhf.remote.models.CatsImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {

    @GET("v1/images/search")
    suspend fun getCatsList(
        @Query("size") size: String = "med",
        @Query("mime_types") mimeTypes: String = "jpg",
        @Query("format") format: String = "json",
        @Query("has_breeds") hasBreeds: Boolean = true,
        @Query("order") order: String = "ASC",
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<CatsImagesResponse>
}