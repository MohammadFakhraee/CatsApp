package com.example.repository.utils

import com.mohammadhf.local.models.CatsImageLocal
import com.mohammadhf.remote.models.CatsImagesResponse

val mockCatsImageRemote =
    CatsImagesResponse(
        id = "xnzzM6MBI",
        url = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        breeds = arrayListOf(mockRemoteBreed)
    )

val mockLocalCatsImage =
    CatsImageLocal(
        catsImageId = "xnzzM6MBI",
        url = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        ownedBreedId = mockLocalBreed.breedId,
        isFavored = false
    )