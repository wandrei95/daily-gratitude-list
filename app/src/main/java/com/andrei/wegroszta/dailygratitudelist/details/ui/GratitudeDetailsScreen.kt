package com.andrei.wegroszta.dailygratitudelist.details.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrei.wegroszta.dailygratitudelist.common.*
import com.andrei.wegroszta.dailygratitudelist.theme.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun GratitudeDetailsScreen(
    gratitudeId: String,
    onBack: () -> Unit,
    viewModel: GratitudeDetailsViewModel = hiltViewModel(),
) {
    var shouldLoad by rememberSaveable { mutableStateOf(true) }
    if (shouldLoad) {
        shouldLoad = false
        viewModel.loadGratitude(gratitudeId)
    }

    val uiState by viewModel.gratitudeDetailsUiState.collectAsStateWithLifecycle()

    var active by rememberSaveable { mutableStateOf(true) }
    if (uiState.isDeleted && active) {
        active = false
        onBack()
        return
    }

    GratitudeDetailsScreen(
        uiState = uiState,
        delete = { viewModel.delete(gratitudeId) },
        onBack = onBack
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun GratitudeDetailsScreen(
    uiState: GratitudeDetailsUiState,
    delete: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarConfig(
                uiState = uiState,
                delete = delete,
                onBack = onBack
            )
        },
        content = { ScreenContent(uiState) }
    )
}

@Composable
private fun TopAppBarConfig(
    uiState: GratitudeDetailsUiState,
    delete: () -> Unit,
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(uiState.date) },
        navigationIcon = {
            BackNavIcon {
                onBack()
            }
        },
        actions = {
            IconButton(onClick = {
                delete()
            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
            }
        }
    )
}

@Composable
private fun ScreenContent(uiState: GratitudeDetailsUiState) {
    FullScreenLoading(uiState.isLoading) {
        Column(
            modifier = Modifier
                .padding(ScreenPadding.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = uiState.date,
                style = MaterialTheme.typography.caption
            )

            Spacer(modifier = Modifier.height(MediumSpacing.dp))

            Text(
                text = uiState.summary,
                style = MaterialTheme.typography.body1
            )

            ImagesCarousel(uiState.photos)

            Tags(uiState)

        }
    }
}

@Composable
private fun ImagesCarousel(
    photos: List<GratitudeImageUiState>
) {
    val selectedPhoto = photos.find { it.isSelected }
    if (selectedPhoto != null) {
        Spacer(modifier = Modifier.height(LargeSpacing.dp))
        ImageFromUrl(
            imgUrl = selectedPhoto.photoUrl,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(MediumSpacing.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(MediumSpacing.dp)) {
            this.items(items = photos) { photo ->
                ImageFromUrlWithAction(
                    imgUrl = photo.photoUrl,
                    modifier = Modifier
                        .width(
                            if (photo.isSelected)
                                GratitudeDetailsSelectedCarouselImageWidth.dp
                            else
                                GratitudeDetailsCarouselImageWidth.dp
                        )
                        .height(
                            if (photo.isSelected)
                                GratitudeDetailsSelectedCarouselImageHeight.dp
                            else
                                GratitudeDetailsCarouselImageHeight.dp
                        ),
                ) {
                    photo.onImageSelected()
                }
            }
        }
    }
}

@Composable
private fun Tags(uiState: GratitudeDetailsUiState) {
    if (uiState.tags.isNotEmpty()) {
        Spacer(modifier = Modifier.height(LargeSpacing.dp))
        FlowRow(
            modifier = Modifier.padding(MediumSpacing.dp),
            mainAxisAlignment = MainAxisAlignment.Center,
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing = MediumSpacing.dp,
            mainAxisSpacing = MediumSpacing.dp
        ) {
            uiState.tags.forEach { tag ->
                Tag(tag = tag)
            }
        }
    }
}
