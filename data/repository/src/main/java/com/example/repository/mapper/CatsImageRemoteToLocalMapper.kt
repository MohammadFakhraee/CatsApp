package com.example.repository.mapper

import com.mohammadhf.local.models.BreedLocal
import com.mohammadhf.local.models.CatsImageLocal
import com.mohammadhf.remote.models.CatsImagesResponse
import javax.inject.Inject

class CatsImageRemoteToLocalMapper @Inject constructor(
    private val breedRemoteToLocalMapper: BreedRemoteToLocalMapper
) {

    operator fun invoke(
        catsImageResponse: CatsImagesResponse
    ): Pair<CatsImageLocal, BreedLocal> =
        CatsImageLocal(
            catsImageId = catsImageResponse.id ?: "",
            url = catsImageResponse.url ?: "",
            ownedBreedId = catsImageResponse.breeds.firstOrNull()?.id ?: "",
            isFavored = false
        ) to breedRemoteToLocalMapper(catsImageResponse.breeds.firstOrNull())
}