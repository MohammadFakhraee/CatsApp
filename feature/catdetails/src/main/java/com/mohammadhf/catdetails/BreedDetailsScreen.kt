package com.mohammadhf.catdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohammadhf.catdetails.BreedDetailsContract.BreedDetailsUiEvent
import com.mohammadhf.catdetails.BreedDetailsContract.BreedDetailsUiState
import com.mohammadhf.catdetails.BreedDetailsContract.BreedDetailsUiEffect
import com.mohammadhf.coreui.base.SIDE_EFFECTS_KEY
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BreedDetailsRoute(
    breedId: String,
    onNavigateBack: () -> Unit,
    launchToast: (message: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BreedDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(breedId) {
        viewModel.setEvent(BreedDetailsUiEvent.CheckForDetails(breedId))
    }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        viewModel.effect.collectLatest {
            when (it) {
                is BreedDetailsUiEffect.LaunchToast -> launchToast(it.message)
                BreedDetailsUiEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    BreedDetailsScreen(
        onUiEvent = { viewModel.setEvent(it) },
        uiState = viewModel.currentState,
        modifier = modifier
    )
}

@Composable
private fun BreedDetailsScreen(
    onUiEvent: (event: BreedDetailsUiEvent) -> Unit,
    uiState: BreedDetailsUiState,
    modifier: Modifier = Modifier
) {
    // TODO: I must implement breeds detail to see the final result
}