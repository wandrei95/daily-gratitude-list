package com.andrei.wegroszta.dailygratitudelist.list.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrei.wegroszta.dailygratitudelist.R
import com.andrei.wegroszta.dailygratitudelist.common.FullScreenLoading
import com.andrei.wegroszta.dailygratitudelist.common.ImageFromUrl
import com.andrei.wegroszta.dailygratitudelist.common.Tag
import com.andrei.wegroszta.dailygratitudelist.theme.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun GratitudeListScreen(
    viewModel: GratitudeListViewModel = hiltViewModel(),
    onGratitudeSelected: (id: String) -> Unit
) {
    val uiState by viewModel.gratitudeListUiState.collectAsStateWithLifecycle()
    GratitudeListScreen(
        uiState = uiState,
        refreshData = {
            viewModel.refreshData()
        },
        onGratitudeSelected = onGratitudeSelected
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun GratitudeListScreen(
    uiState: GratitudeListUiState,
    refreshData: () -> Unit,
    onGratitudeSelected: (id: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarConfig(refreshData)
        },
        content = { ScreenContent(uiState, onGratitudeSelected) }
    )
}

@Composable
private fun TopAppBarConfig(refreshData: () -> Unit) {
    var dropDownMenuExpanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(stringResource(R.string.gratitude_list_title)) },
        actions = {
            IconButton(onClick = {
                refreshData()
            }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
            }

            //dummy elements
            IconButton(onClick = {
                dropDownMenuExpanded = true
            }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = {
                    dropDownMenuExpanded = false
                },
                offset = DpOffset(x = 10.dp, y = (-60).dp)
            ) {
                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                }
                ) {
                    Text("Action 1")
                }
                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                }
                ) {
                    Text("Action 2")
                }
                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                }
                ) {
                    Text("Action 3")
                }
            }
        }
    )
}

@Composable
private fun ScreenContent(
    uiState: GratitudeListUiState,
    onGratitudeSelected: (id: String) -> Unit
) {
    FullScreenLoading(uiState.isLoading) {
        LazyColumn(modifier = Modifier.padding(vertical = SmallSpacing.dp)) {
            items(items = uiState.items) { gratitudeUiState ->
                GratitudeItem(gratitudeUiState) {
                    onGratitudeSelected(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun GratitudeItem(
    gratitudeUiState: GratitudeListItemUiState,
    onGratitudeSelected: (id: String) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = SmallSpacing.dp, horizontal = MediumSpacing.dp),
        elevation = SmallSpacing.dp,
        shape = RoundedCornerShape(MediumSpacing.dp),
        onClick = { onGratitudeSelected(gratitudeUiState.id) }
    ) {
        Column(modifier = Modifier.padding(ScreenPadding.dp)) {
            Text(
                text = gratitudeUiState.date,
                style = MaterialTheme.typography.caption
            )

            Spacer(modifier = Modifier.height(MediumSpacing.dp))

            Text(
                text = gratitudeUiState.summary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1
            )

            if (gratitudeUiState.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(LargeSpacing.dp))
            }

            Tags(gratitudeUiState.tags)

            gratitudeUiState.image?.let { imgUrl ->
                Spacer(modifier = Modifier.height(LargeSpacing.dp))
                ImageFromUrl(
                    imgUrl = imgUrl,
                    modifier = Modifier.height(GratitudeListImageHeight.dp)
                )
            }

        }
    }
}

@Composable
private fun Tags(tags: List<String>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(MediumSpacing.dp)) {
        items(items = tags) { tag ->
            Tag(tag = tag)
        }
    }
}
