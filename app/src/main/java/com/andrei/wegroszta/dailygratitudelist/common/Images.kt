package com.andrei.wegroszta.dailygratitudelist.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.andrei.wegroszta.dailygratitudelist.theme.Teal200

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageFromUrlWithAction(
    imgUrl: String,
    modifier: Modifier = Modifier,
    onClickAction: (() -> Unit)
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Teal200,
        modifier = modifier,
        onClick = { onClickAction() }
    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun ImageFromUrl(
    imgUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Teal200,
        modifier = modifier,
    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun BackNavIcon(onBack: () -> Unit) {
    IconButton(onClick = {
        onBack()
    }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Navigation icon"
        )
    }
}
