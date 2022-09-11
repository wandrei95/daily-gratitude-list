package com.andrei.wegroszta.dailygratitudelist.list.ui

import com.andrei.wegroszta.dailygratitudelist.common.ErrorHandlingViewModel
import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import com.andrei.wegroszta.dailygratitudelist.ext.toFormattedDate
import com.andrei.wegroszta.dailygratitudelist.list.data.GratitudeListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GratitudeListViewModel @Inject constructor(
    private val repository: GratitudeListRepository
) : ErrorHandlingViewModel() {

    private val _gratitudeListUiState = MutableStateFlow(GratitudeListUiState())
    val gratitudeListUiState: StateFlow<GratitudeListUiState> = _gratitudeListUiState.asStateFlow()

    init {
        launchWithErrorHandling {
            launch {
                listenGratitudeChanges()
            }
            launch {
                loadGratitudes()
            }
        }
    }

    private suspend fun listenGratitudeChanges() {
        repository.getGratitudeListFlow().collect { gratitudes ->
            val sorted = gratitudes.sortedByDescending { it.date }
            _gratitudeListUiState.update { uiState ->
                uiState.copy(
                    isLoading = sorted.isEmpty(),
                    items = sorted.toGratitudeListItemUiStates()
                )
            }
        }
    }

    private suspend fun loadGratitudes() {
        _gratitudeListUiState.update { uiState -> uiState.copy(isLoading = true) }
        repository.loadGratitudes()
    }

    private fun List<Gratitude>.toGratitudeListItemUiStates(): List<GratitudeListItemUiState> =
        map { it.toGratitudeListItemUiState() }

    private fun Gratitude.toGratitudeListItemUiState() = GratitudeListItemUiState(
        id = this.id,
        date = this.date.toFormattedDate(),
        summary = this.summary,
        image = this.photoUrls.firstOrNull(),
        tags = this.tags
    )
}
