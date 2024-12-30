package com.mohammadhf.models.cats

data class CatsImageModel(
    val id: String,
    val imageUrl: String,
    val breedName: String,
    val breedId: String,
    val isFavorite: Boolean
)