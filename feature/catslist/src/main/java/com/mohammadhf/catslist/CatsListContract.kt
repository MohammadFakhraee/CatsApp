package com.mohammadhf.catslist

import com.mohammadhf.coreui.base.BaseContract
import com.mohammadhf.models.cats.CatsImageModel

class CatsListContract {

    sealed interface CatsListUiEvent : BaseContract.UiEvent {
        data class OnCatDetailClicked(val cat: CatsImageModel) : CatsListUiEvent
        data class OnToggleFavorite(val cat: CatsImageModel) : CatsListUiEvent
    }

    data class CatsListUiState(
        val cats: List<CatsImageModel>,
    ) : BaseContract.UiState {

        companion object {
            val Initial = CatsListUiState(
                cats = arrayListOf()
            )
        }
    }

    sealed interface CatsListUiEffect : BaseContract.UiEffect {
        data class LaunchToast(val message: String) : CatsListUiEffect
        data class HandleNavigatingToDetails(val breedId: String) : CatsListUiEffect
    }
}