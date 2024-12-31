package com.mohammadhf.catdetails

import com.mohammadhf.coreui.base.BaseContract
import com.mohammadhf.models.breed.BreedModel

class BreedDetailsContract {

    sealed interface BreedDetailsUiEvent : BaseContract.UiEvent {
        data object OnBackClicked : BreedDetailsUiEvent
        data class CheckForDetails(val breedId: String) : BreedDetailsUiEvent
    }

    data class BreedDetailsUiState(
        val breedModel: BreedModel
    ) : BaseContract.UiState {

        companion object {
            val Initial = BreedDetailsUiState(
                breedModel = BreedModel(
                    breedId = "",
                    name = "--",
                    origin = "--",
                    description = "--",
                    adaptability = -1,
                    childFriendly = -1
                )
            )
        }
    }

    sealed interface BreedDetailsUiEffect : BaseContract.UiEffect {
        data object NavigateBack : BreedDetailsUiEffect
        data class LaunchToast(val message: String) : BreedDetailsUiEffect
    }
}