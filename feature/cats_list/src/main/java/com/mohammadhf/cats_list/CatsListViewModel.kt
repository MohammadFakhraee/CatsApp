package com.mohammadhf.cats_list

import androidx.lifecycle.viewModelScope
import com.mohammadhf.cats_list.CatsListContract.CatsListUiEffect
import com.mohammadhf.cats_list.CatsListContract.CatsListUiEvent
import com.mohammadhf.cats_list.CatsListContract.CatsListUiState
import com.mohammadhf.core.utils.CoroutineContextProvider
import com.mohammadhf.core_ui.base.BaseViewModel
import com.mohammadhf.models.base.Resource
import com.mohammadhf.models.cats.CatsImageModel
import com.mohammadhf.use_case.cats.StreamAllCatsUseCase
import com.mohammadhf.use_case.cats.ToggleCatFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CatsListViewModel @Inject constructor(
    private val streamAllCatsUseCase: StreamAllCatsUseCase,
    private val toggleCatFavoriteUseCase: ToggleCatFavoriteUseCase,
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseViewModel<CatsListUiEvent, CatsListUiState, CatsListUiEffect>() {

    /**
     * An empty flow that never emits data which can be used to request fetch initial data
     * when any subscriber is present.
     *
     * Collect it in the UI to make sure we only fetch data when UI is present on the screen.
     */
    val initialData = emptyFlow<Unit>().onStart { startObservingData() }.viewModelStateIn(
        started = SharingStarted.WhileSubscribed(2000L),
        initialValue = Unit
    )

    override fun createInitialState(): CatsListUiState = CatsListUiState.Initial

    private fun startObservingData() {
        viewModelScope.launch(coroutineContextProvider.io) {
            streamAllCatsUseCase(Unit).onEach {
                when (it) {
                    is Resource.Failure -> withContext(coroutineContextProvider.ui) {
                        // The message is hard coded, considering it is a server side message.
                        setEffect { CatsListUiEffect.LaunchToast("There is a problem in loading data") }
                    }

                    is Resource.Success -> withContext(coroutineContextProvider.ui) {
                        setState { copy(cats = it.data) }
                    }
                }
            }.collect()
        }
    }

    override fun handleEvent(event: CatsListUiEvent) {
        when (event) {
            is CatsListUiEvent.OnCatDetailClicked -> handleOnCatDetailClicked(event.cat)
            is CatsListUiEvent.OnToggleFavorite -> handleOnToggleFavorite(event.cat)
        }
    }

    private fun handleOnCatDetailClicked(catsImageModel: CatsImageModel) {
        setEffect { CatsListUiEffect.HandleNavigatingToDetails(catsImageModel.breedId) }
    }

    private fun handleOnToggleFavorite(catsImageModel: CatsImageModel) {
        viewModelScope.launch(coroutineContextProvider.io) {
            toggleCatFavoriteUseCase(catsImageModel.id)
        }
    }
}