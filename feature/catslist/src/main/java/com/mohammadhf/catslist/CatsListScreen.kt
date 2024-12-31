package com.mohammadhf.catslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.mohammadhf.cats_list.R
import com.mohammadhf.catslist.CatsListContract.CatsListUiEvent
import com.mohammadhf.catslist.CatsListContract.CatsListUiState
import com.mohammadhf.catslist.CatsListContract.CatsListUiEffect
import com.mohammadhf.coreui.base.SIDE_EFFECTS_KEY
import com.mohammadhf.models.cats.CatsImageModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CatsListRoute(
    onNavigateToDetails: (id: String) -> Unit,
    launchToast: (message: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatsListViewModel = hiltViewModel()
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        viewModel.initialData.collect()
    }

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
    onUiEvent: (CatsListUiEvent) -> Unit,
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
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .clickable(
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
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(96.dp),
            color = MaterialTheme.colorScheme.onSurface,
            shape = CircleShape
        ) {
            AsyncImage(
                model = catsImageModel.imageUrl,
                contentDescription = null,
                placeholder = null
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(text = catsImageModel.breedName)
        Spacer(Modifier.weight(1f))

        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onToggleClicked
                ),
            imageVector = ImageVector.vectorResource(R.drawable.ic_start),
            tint = if (catsImageModel.isFavorite) MaterialTheme.colorScheme.primary
            else Color.LightGray.copy(alpha = 0.5f),
            contentDescription = null
        )
    }
}