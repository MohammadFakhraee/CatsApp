package com.mohammadhf.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedLocal(
    @PrimaryKey(autoGenerate = false) val breedId: String,
    val suppressedTail: Int,
    val wikipediaUrl: String,
    val origin: String,
    val description: String,
    val experimental: Int,
    val lifeSpan: String,
    val cfaUrl: String,
    val rare: Int,
    val countryCodes: String,
    val lap: Int,
    val shortLegs: Int,
    val sheddingLevel: Int,
    val dogFriendly: Int,
    val natural: Int,
    val rex: Int,
    val healthIssues: Int,
    val hairless: Int,
    val altNames: String,
    val adaptability: Int,
    val vocalisation: Int,
    val intelligence: Int,
    val socialNeeds: Int,
    val countryCode: String,
    val childFriendly: Int,
    val vcahospitalsUrl: String,
    val temperament: String,
    val name: String,
    val vetstreetUrl: String,
    val grooming: Int,
    val hypoallergenic: Int,
    val indoor: Int,
    val energyLevel: Int,
    val strangerFriendly: Int,
    val referenceImageId: String,
    val affectionLevel: Int
)
