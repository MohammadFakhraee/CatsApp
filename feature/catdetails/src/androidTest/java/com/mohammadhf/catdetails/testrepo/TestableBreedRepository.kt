package com.mohammadhf.catdetails.testrepo

import com.mohammadhf.local.models.BreedLocal
import com.mohammadhf.repository.breed.BreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TestableBreedRepository : BreedRepository {

    private val breedListStateFlow = MutableStateFlow(mockBreedList)

    override fun streamAllBreeds(): Flow<List<BreedLocal>> =
        breedListStateFlow

    override suspend fun getById(id: String): BreedLocal {
        return breedListStateFlow.value.find { it.breedId == id }
            ?: throw IllegalArgumentException("Breed Id not found: $id")
    }
}

private val mockBreedList: List<BreedLocal> = arrayListOf(
    BreedLocal(
        breedId = "BREED1",
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
    ),
    BreedLocal(
        breedId = "BREED2",
        suppressedTail = 0,
        wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
        origin = "Iran",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        experimental = 0,
        lifeSpan = "14 - 15",
        cfaUrl = "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
        rare = 0,
        countryCodes = "IR",
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
        countryCode = "IR",
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
    ),
    BreedLocal(
        breedId = "BREED3",
        suppressedTail = 0,
        wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
        origin = "Iraq",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        experimental = 0,
        lifeSpan = "14 - 15",
        cfaUrl = "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
        rare = 0,
        countryCodes = "IR",
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
        countryCode = "IR",
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