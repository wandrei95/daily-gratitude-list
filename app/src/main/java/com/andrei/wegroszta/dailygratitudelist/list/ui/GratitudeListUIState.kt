package com.andrei.wegroszta.dailygratitudelist.list.ui

data class GratitudeListUiState(
    val isLoading: Boolean = true,
    val items: List<GratitudeListItemUiState> = emptyList()
)

data class GratitudeListItemUiState(
    val id: String,
    val date: String,
    val summary: String,
    val image: String?,
    val tags: List<String>
)
