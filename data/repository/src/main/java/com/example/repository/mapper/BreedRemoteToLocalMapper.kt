package com.example.repository.mapper

import com.mohammadhf.local.models.BreedLocal
import com.mohammadhf.remote.models.BreedsItem
import javax.inject.Inject

class BreedRemoteToLocalMapper @Inject constructor() {

    operator fun invoke(breedsItem: BreedsItem?): BreedLocal =
        BreedLocal(
            breedId = breedsItem?.id ?: "",
            suppressedTail = breedsItem?.suppressedTail ?: -1,
            wikipediaUrl = breedsItem?.wikipediaUrl ?: "",
            origin = breedsItem?.origin ?: "",
            description = breedsItem?.description ?: "",
            experimental = breedsItem?.experimental ?: -1,
            lifeSpan = breedsItem?.lifeSpan ?: "",
            cfaUrl = breedsItem?.cfaUrl ?: "",
            rare = breedsItem?.rare ?: -1,
            countryCodes = breedsItem?.countryCodes ?: "",
            lap = breedsItem?.lap ?: -1,
            shortLegs = breedsItem?.shortLegs ?: -1,
            sheddingLevel = breedsItem?.sheddingLevel ?: -1,
            dogFriendly = breedsItem?.dogFriendly ?: -1,
            natural = breedsItem?.natural ?: -1,
            rex = breedsItem?.rex ?: -1,
            healthIssues = breedsItem?.healthIssues ?: -1,
            hairless = breedsItem?.hairless ?: -1,
            altNames = breedsItem?.altNames ?: "",
            adaptability = breedsItem?.adaptability ?: -1,
            vocalisation = breedsItem?.vocalisation ?: -1,
            intelligence = breedsItem?.intelligence ?: -1,
            socialNeeds = breedsItem?.socialNeeds ?: -1,
            countryCode = breedsItem?.countryCode ?: "",
            childFriendly = breedsItem?.childFriendly ?: -1,
            vcahospitalsUrl = breedsItem?.vcahospitalsUrl ?: "",
            temperament = breedsItem?.temperament ?: "",
            name = breedsItem?.name ?: "",
            vetstreetUrl = breedsItem?.vetstreetUrl ?: "",
            grooming = breedsItem?.grooming ?: -1,
            hypoallergenic = breedsItem?.hypoallergenic ?: -1,
            indoor = breedsItem?.indoor ?: -1,
            energyLevel = breedsItem?.energyLevel ?: -1,
            strangerFriendly = breedsItem?.strangerFriendly ?: -1,
            referenceImageId = breedsItem?.referenceImageId ?: "",
            affectionLevel = breedsItem?.affectionLevel ?: -1
        )
}