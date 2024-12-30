package com.mohammadhf.repository.utils

import com.mohammadhf.local.models.CatsImageLocal
import com.mohammadhf.remote.models.CatsImagesResponse

val mockCatsImageServerResponse =
    """
[
    {
        "breeds": [
            {
                "weight": {
                    "imperial": "7  -  10",
                    "metric": "3 - 5"
                },
                "id": "abys",
                "name": "Abyssinian",
                "cfa_url": "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
                "vetstreet_url": "http://www.vetstreet.com/cats/abyssinian",
                "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
                "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
                "origin": "Egypt",
                "country_codes": "EG",
                "country_code": "EG",
                "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                "life_span": "14 - 15",
                "indoor": 0,
                "lap": 1,
                "alt_names": "",
                "adaptability": 5,
                "affection_level": 5,
                "child_friendly": 3,
                "dog_friendly": 4,
                "energy_level": 5,
                "grooming": 1,
                "health_issues": 2,
                "intelligence": 5,
                "shedding_level": 2,
                "social_needs": 5,
                "stranger_friendly": 5,
                "vocalisation": 1,
                "experimental": 0,
                "hairless": 0,
                "natural": 1,
                "rare": 0,
                "rex": 0,
                "suppressed_tail": 0,
                "short_legs": 0,
                "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                "hypoallergenic": 0,
                "reference_image_id": "0XYvRd7oD"
            }
        ],
        "id": "xnzzM6MBI",
        "url": "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        "width": 2592,
        "height": 1629
    },
    {
        "breeds": [
            {
                "weight": {
                    "imperial": "7  -  10",
                    "metric": "3 - 5"
                },
                "id": "uyts",
                "name": "Abyssinian",
                "cfa_url": "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
                "vetstreet_url": "http://www.vetstreet.com/cats/abyssinian",
                "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
                "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
                "origin": "Egypt",
                "country_codes": "EG",
                "country_code": "EG",
                "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                "life_span": "14 - 15",
                "indoor": 0,
                "lap": 1,
                "alt_names": "",
                "adaptability": 5,
                "affection_level": 5,
                "child_friendly": 3,
                "dog_friendly": 4,
                "energy_level": 5,
                "grooming": 1,
                "health_issues": 2,
                "intelligence": 5,
                "shedding_level": 2,
                "social_needs": 5,
                "stranger_friendly": 5,
                "vocalisation": 1,
                "experimental": 0,
                "hairless": 0,
                "natural": 1,
                "rare": 0,
                "rex": 0,
                "suppressed_tail": 0,
                "short_legs": 0,
                "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                "hypoallergenic": 0,
                "reference_image_id": "0XYvRd7oD"
            }
        ],
        "id": "iouoiu",
        "url": "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        "width": 2592,
        "height": 1629
    }
]
""".trimIndent()

val mockCatsImageRemote1 =
    CatsImagesResponse(
        id = "xnzzM6MBI",
        url = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        breeds = arrayListOf(mockRemoteBreed1)
    )

val mockCatsImageRemote2 =
    CatsImagesResponse(
        id = "iouoiu",
        url = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        breeds = arrayListOf(mockRemoteBreed2)
    )

val mockLocalCatsImage1 =
    CatsImageLocal(
        catsImageId = "xnzzM6MBI",
        url = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        ownedBreedId = mockLocalBreed1.breedId,
        isFavored = false
    )

val mockLocalCatsImage2 =
    CatsImageLocal(
        catsImageId = "iouoiu",
        url = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        ownedBreedId = mockLocalBreed2.breedId,
        isFavored = false
    )

val mockRemoteCatsImageList = arrayListOf(
    mockCatsImageRemote1, mockCatsImageRemote2
)

val mockLocalCatsImageList = arrayListOf(
    mockLocalCatsImage1, mockLocalCatsImage2
)