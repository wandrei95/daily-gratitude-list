package com.andrei.wegroszta.dailygratitudelist.details.ui

data class GratitudeDetailsUiState(
    val isDeleted: Boolean = false,
    val isLoading: Boolean = true,
    val date: String = "",
    val summary: String = "",
    val photos: List<GratitudeImageUiState> = emptyList(),
    val tags: List<String> = emptyList()
)

data class GratitudeImageUiState(
    val photoUrl: String,
    val isSelected: Boolean,
    val onImageSelected: () -> Unit
)

