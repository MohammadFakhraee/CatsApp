package com.mohammadhf.catslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohammadhf.catslist.CatsListContract.CatsListUiEvent
import com.mohammadhf.catslist.CatsListContract.CatsListUiState
import com.mohammadhf.catslist.CatsListContract.CatsListUiEffect
import com.mohammadhf.coreui.base.SIDE_EFFECTS_KEY
import com.mohammadhf.models.cats.CatsImageModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CatsListRoute(
    onNavigateToDetails: (id: String) -> Unit,
    launchToast: (message: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatsListViewModel = hiltViewModel()
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        viewModel.effect.collectLatest {
            when (it) {
                is CatsListUiEffect.HandleNavigatingToDetails -> onNavigateToDetails(it.breedId)
                is CatsListUiEffect.LaunchToast -> launchToast(it.message)
            }
        }
    }

    CatsListScreen(
        modifier = modifier,
        onUiEvent = { viewModel.setEvent(it) },
        uiState = viewModel.currentState
    )
}

@Composable
private fun CatsListScreen(
    onUiEvent : (CatsListUiEvent) -> Unit,
    uiState: CatsListUiState,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        LazyColumn {
            items(
                items = uiState.cats,
                key = { it.id },
            ) {
                CatItem(
                    catsImageModel = it,
                    onToggleClicked = { onUiEvent(CatsListUiEvent.OnToggleFavorite(it)) },
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onUiEvent(CatsListUiEvent.OnCatDetailClicked(it)) }
                    )
                )
            }
        }
    }
}

@Composable
private fun CatItem(
    catsImageModel: CatsImageModel,
    onToggleClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: I must implement UI for the cat to see the final result
}