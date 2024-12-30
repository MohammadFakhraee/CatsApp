package com.mohammadhf.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatsImageLocal(
    @PrimaryKey(autoGenerate = false) val catsImageId: String,
    val url: String,
    val ownedBreedId: String,
    val isFavored: Boolean = false
)