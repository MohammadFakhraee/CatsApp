package com.mohammadhf.remote.utils

import com.mohammadhf.remote.models.BreedsItem
import com.mohammadhf.remote.models.CatsImagesResponse

val mockCatsImagesResponse1 = """
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
    }
]
""".trimIndent()

val mockCatsImagesResult1: List<CatsImagesResponse> = arrayListOf(
    CatsImagesResponse(
        id = "xnzzM6MBI",
        url = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        breeds = arrayListOf(
            BreedsItem(
                id = "abys",
                suppressedTail = 0,
                wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                origin = "Egypt",
                description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                experimental = 0,
                lifeSpan = "14 - 15",
                cfaUrl = "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
                rare = 0,
                countryCodes = "EG",
                lap = 1,
                shortLegs = 0,
                sheddingLevel = 2,
                dogFriendly = 4,
                natural = 1,
                rex = 0,
                healthIssues = 2,
                hairless = 0,
                altNames = "",
                adaptability = 5,
                vocalisation = 1,
                intelligence = 5,
                socialNeeds = 5,
                countryCode = "EG",
                childFriendly = 3,
                vcahospitalsUrl = "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
                temperament = "Active, Energetic, Independent, Intelligent, Gentle",
                name = "Abyssinian",
                vetstreetUrl = "http://www.vetstreet.com/cats/abyssinian",
                grooming = 1,
                hypoallergenic = 0,
                indoor = 0,
                energyLevel = 5,
                strangerFriendly = 5,
                referenceImageId = "0XYvRd7oD",
                affectionLevel = 5
            )
        )
    )
)

val mockCatsImagesResponse2 = """
[
    {
        "breeds": [],
        "id": "xnzzM6MBI",
        "url": "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg",
        "width": 2592,
        "height": 1629
    },
    {
        "breeds": [],
        "id": "EHG3sOpAM",
        "url": "https://cdn2.thecatapi.com/images/EHG3sOpAM.jpg",
        "width": 1600,
        "height": 1067
    }
]
""".trimIndent()