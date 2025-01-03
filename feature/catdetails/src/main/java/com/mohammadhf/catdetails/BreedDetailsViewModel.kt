package com.mohammadhf.catdetails

import androidx.lifecycle.viewModelScope
import com.mohammadhf.catdetails.BreedDetailsContract.BreedDetailsUiEffect
import com.mohammadhf.catdetails.BreedDetailsContract.BreedDetailsUiEvent
import com.mohammadhf.catdetails.BreedDetailsContract.BreedDetailsUiState
import com.mohammadhf.core.utils.CoroutineContextProvider
import com.mohammadhf.coreui.base.BaseViewModel
import com.mohammadhf.models.base.Resource
import com.mohammadhf.usecase.breed.GetBreedByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
    private val getBreedByIdUseCase: GetBreedByIdUseCase,
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseViewModel<BreedDetailsUiEvent, BreedDetailsUiState, BreedDetailsUiEffect>() {

    override fun createInitialState(): BreedDetailsUiState = BreedDetailsUiState.Initial

    override fun handleEvent(event: BreedDetailsUiEvent) {
        when (event) {
            is BreedDetailsUiEvent.OnBackClicked -> handleOnBackClicked()
            is BreedDetailsUiEvent.CheckForDetails -> handleCheckForDetails(event.breedId)
        }
    }

    private fun handleOnBackClicked() {
        setEffect { BreedDetailsUiEffect.NavigateBack }
    }

    private fun handleCheckForDetails(breedId: String) {
        viewModelScope.launch(coroutineContextProvider.io) {
            when (val r = getBreedByIdUseCase(param = breedId)) {
                is Resource.Failure -> withContext(coroutineContextProvider.ui) {
                    setEffect { BreedDetailsUiEffect.LaunchToast("Couldn't receive breed details") }
                    setEffect { BreedDetailsUiEffect.NavigateBack }
                }

                is Resource.Success -> withContext(coroutineContextProvider.ui) {
                    setState { copy(breedModel = r.data) }
                }
            }
        }
    }
}