package com.andrei.wegroszta.dailygratitudelist.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Tag(tag: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.onPrimary,
    ) {
        Text(
            text = tag,
            modifier = Modifier.padding(all = 4.dp),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primaryVariant
        )
    }
}
