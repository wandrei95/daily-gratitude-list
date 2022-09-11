package com.andrei.wegroszta.dailygratitudelist.details.ui

import com.andrei.wegroszta.dailygratitudelist.common.ErrorHandlingViewModel
import com.andrei.wegroszta.dailygratitudelist.details.data.GratitudeDetailsRepository
import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import com.andrei.wegroszta.dailygratitudelist.ext.toFormattedDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GratitudeDetailsViewModel @Inject constructor(
    private val repository: GratitudeDetailsRepository
) : ErrorHandlingViewModel() {

    private val _gratitudeDetailsUiState = MutableStateFlow(GratitudeDetailsUiState())
    val gratitudeDetailsUiState: StateFlow<GratitudeDetailsUiState> =
        _gratitudeDetailsUiState.asStateFlow()

    fun loadGratitude(id: String) = launchWithErrorHandling {
        listenForGratitudeChanges(id)
    }

    private suspend fun listenForGratitudeChanges(id: String) {
        repository.getGratitudeFlow(id).collect { gratitude ->
            _gratitudeDetailsUiState.update { gratitude.toGratitudeDetailsUiState() }
        }
    }

    fun updateSummary(id: String, summary: String) {
        _gratitudeDetailsUiState.update { it.copy(isLoading = true) }

        launchWithErrorHandling {
            repository.updateSummary(id, summary)
            _gratitudeDetailsUiState.update { it.copy(isLoading = false) }
        }
    }

    fun delete(id: String) {
        _gratitudeDetailsUiState.update { it.copy(isLoading = true) }

        launchWithErrorHandling {
            repository.delete(id)
            _gratitudeDetailsUiState.update { it.copy(isLoading = false, isDeleted = true) }
        }
    }

    private fun Gratitude.toGratitudeDetailsUiState() = GratitudeDetailsUiState(
        isLoading = false,
        date = this.date.toFormattedDate(),
        summary = this.summary,
        photos = this.photoUrls.toGratitudeImageUiStates(),
        tags = this.tags
    )

    private fun List<String>.toGratitudeImageUiStates(): List<GratitudeImageUiState> =
        mapIndexed { index, s ->
            GratitudeImageUiState(s, index == 0) {
                selectPhoto(s)
            }
        }

    private fun selectPhoto(selectedPhotoURl: String) {
        _gratitudeDetailsUiState.update {
            it.copy(
                photos = it.photos.map { photo ->
                    photo.copy(
                        isSelected = selectedPhotoURl == photo.photoUrl
                    )
                }
            )
        }
    }
}
