package com.mohammadhf.models.breed

data class BreedModel(
    val breedId: String,
    val name: String,
    val origin: String,
    val description: String,
    val adaptability: Int,
    val childFriendly: Int
)